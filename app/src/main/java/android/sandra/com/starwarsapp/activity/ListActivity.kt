package android.sandra.com.starwarsapp.activity

import android.content.Intent
import android.os.Bundle
import android.sandra.com.starwarsapp.*
import android.sandra.com.starwarsapp.entity.*
import android.sandra.com.starwarsapp.net.SWAPIService
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private var categoryIndex: Int = CATEGORY_BUNDLE_DEFAULT

    private val swapiService by lazy {
        SWAPIService.create()
    }

    private var pbLoad: ProgressBar? = null

    private var page = 1

    private var adapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null
    private var btnLoadMore: Button? = null

    private var films = mutableListOf<Film>()
    private var people = mutableListOf<Person>()
    private var planets = mutableListOf<Planet>()
    private var spaceships = mutableListOf<Spaceship>()
    private var vehicles = mutableListOf<Vehicle>()
    private var species = mutableListOf<Species>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        categoryIndex = intent.getIntExtra(CATEGORY_BUNDLE_KEY, CATEGORY_BUNDLE_DEFAULT)

        val actionBar = supportActionBar
        actionBar?.title = resources.getStringArray(R.array.sw_categories)[categoryIndex]

        pbLoad = findViewById(R.id.pb_load_list)

        initListView()

        disposable = getData(categoryIndex)

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun initListView() {
        adapter = ArrayAdapter(this, android.R.layout.simple_selectable_list_item)

        listView = findViewById(R.id.lv_list)

        listView?.adapter = adapter
        listView?.emptyView = findViewById(R.id.tv_empty_list)

        listView?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            startActivity(initActivityIntent(position))
        }

        btnLoadMore = Button(this)
        btnLoadMore?.text = getString(R.string.load_more)
        btnLoadMore?.setPadding(15, 15, 15, 15)
        btnLoadMore?.setOnClickListener {
            page++
            disposable = getData(categoryIndex)
        }
    }

    private fun getData(id: Int): Disposable? {
        visibilityProgressBar(true)

        return when (id) {
            CATEGORIES.FILMS.id -> swapiService.getFilms(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                val sortedData = result.results.sortedBy { it.episodeId }
                                films.addAll(sortedData)
                                showData(sortedData.map { it.title }, result.next != null) },
                            { err -> showError(err.message) }
                    )

            CATEGORIES.PEOPLE.id -> swapiService.getPeople(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                people.addAll(result.results)
                                showData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )


            CATEGORIES.PLANETS.id -> swapiService.getPlanets(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                planets.addAll(result.results)
                                showData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )

            CATEGORIES.SPACESHIPS.id -> swapiService.getSpaceships(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                spaceships.addAll(result.results)
                                showData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )

            CATEGORIES.VEHICLES.id -> swapiService.getVehicles(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                vehicles.addAll(result.results)
                                showData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )

            CATEGORIES.SPECIES.id -> swapiService.getSpecies(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                species.addAll(result.results)
                                showData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )

            else -> null
        }
    }

    private fun showData(data: List<String>, displayLoadMore: Boolean) {
        visibilityProgressBar(false)

        adapter?.addAll(data)

        if (displayLoadMore && listView?.footerViewsCount == 0) {
            listView?.addFooterView(btnLoadMore)
        } else if (!displayLoadMore) {
            listView?.removeFooterView(btnLoadMore)
        }
    }

    private fun initActivityIntent(position: Int): Intent {
        when (categoryIndex) {

            CATEGORIES.FILMS.id -> {
                intent = Intent(this, FilmActivity::class.java)
                intent.putExtra(FILM_BUNDLE_KEY, films[position])
            }

            CATEGORIES.PEOPLE.id -> {
                intent = Intent(this, PeopleActivity::class.java)
                intent.putExtra(PEOPLE_BUNDLE_KEY, people[position])
            }

            CATEGORIES.PLANETS.id -> {
                intent = Intent(this, PlanetsActivity::class.java)
                intent.putExtra(PLANET_BUNDLE_KEY, planets[position])
            }

            CATEGORIES.SPACESHIPS.id -> {
                intent = Intent(this, SpaceshipActivity::class.java)
                intent.putExtra(SPACESHIP_BUNDLE_KEY, spaceships[position])
            }

            CATEGORIES.VEHICLES.id -> {
                intent = Intent(this, VehicleActivity::class.java)
                intent.putExtra(VEHICLE_BUNDLE_KEY, vehicles[position])
            }

            CATEGORIES.SPECIES.id -> {
                intent = Intent(this, SpeciesActivity::class.java)
                intent.putExtra(SPECIES_BUNDLE_KEY, species[position])
            }
        }

        return intent
    }

    private fun visibilityProgressBar(visible: Boolean) {
        pbLoad?.visibility = if (visible) VISIBLE else GONE
    }

    private fun showError(msg: String? = "") {
        visibilityProgressBar(false)

        Log.d("SW", msg)

        Toast.makeText(this, getString(R.string.could_not_load_data), LENGTH_LONG).show()
    }
}