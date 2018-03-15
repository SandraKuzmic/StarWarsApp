package android.sandra.com.starwarsapp.activity

import android.os.Bundle
import android.sandra.com.starwarsapp.CATEGORIES
import android.sandra.com.starwarsapp.CATEGORY_BUNDLE_DEFAULT
import android.sandra.com.starwarsapp.CATEGORY_BUNDLE_KEY
import android.sandra.com.starwarsapp.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        categoryIndex = intent.getIntExtra(CATEGORY_BUNDLE_KEY, CATEGORY_BUNDLE_DEFAULT)

        val actionBar = supportActionBar
        actionBar?.title = resources.getStringArray(R.array.sw_categories)[categoryIndex]

        pbLoad = findViewById(R.id.pb_load_list)

        adapter = ArrayAdapter(this, android.R.layout.simple_selectable_list_item)

        listView = findViewById(R.id.lv_list)

        listView?.adapter = adapter
        listView?.emptyView = findViewById(R.id.tv_empty_list)

        disposable = getData(categoryIndex)

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun getData(id: Int): Disposable? {
        visibilityProgressBar(true)

        return when (id) {
            CATEGORIES.FILMS.id -> swapiService.getFilms(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.sortedBy { it.episodeId }.map { it.title }, result.next != null) },
                            { err -> showError(err.message) }
                    )

            CATEGORIES.PEOPLE.id -> swapiService.getPeople(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )


            CATEGORIES.PLANETS.id -> swapiService.getPlanets(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )

            CATEGORIES.SPACESHIPS.id -> swapiService.getSpaceships(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )

            CATEGORIES.VEHICLES.id -> swapiService.getVehicles(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )

            CATEGORIES.SPECIES.id -> swapiService.getSpecies(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }, result.next != null) },
                            { showError() }
                    )

            else -> null
        }
    }

    private fun setData(data: List<String>, displayLoadMore: Boolean) {
        visibilityProgressBar(false)
        setListView(data, displayLoadMore)
    }

    private fun setListView(data: List<String>, displayLoadMore: Boolean) {

        adapter?.addAll(data)

        if (displayLoadMore && page == 1) {
            val btnLoadMore = Button(this)
            btnLoadMore.text = getString(R.string.load_more)
            btnLoadMore.setPadding(15, 15, 15, 15)

            btnLoadMore.setOnClickListener {
                page++
                disposable = getData(categoryIndex)
            }

            listView?.addFooterView(btnLoadMore)
        }


    }

    private fun visibilityProgressBar(visible: Boolean) {
        pbLoad?.visibility = if (visible) VISIBLE else GONE
    }

    private fun showError(msg: String? = "") {
        Log.d("SW", msg)
        Toast.makeText(this, getString(R.string.could_not_load_data), LENGTH_LONG).show()
    }
}