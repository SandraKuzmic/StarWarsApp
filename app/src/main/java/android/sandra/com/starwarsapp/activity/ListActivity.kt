package android.sandra.com.starwarsapp.activity

import android.os.Bundle
import android.sandra.com.starwarsapp.CATEGORIES
import android.sandra.com.starwarsapp.CATEGORY_BUNDLE_DEFAULT
import android.sandra.com.starwarsapp.CATEGORY_BUNDLE_KEY
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.net.SWAPIService
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val swapiService by lazy {
        SWAPIService.create()
    }

    private var pbLoad: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val categoryIndex = intent.getIntExtra(CATEGORY_BUNDLE_KEY, CATEGORY_BUNDLE_DEFAULT)
        val actionBar = supportActionBar
        actionBar?.title = resources.getStringArray(R.array.sw_categories)[categoryIndex]

        pbLoad = findViewById(R.id.pb_load_list)

        disposable = getData(categoryIndex)

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun getData(id: Int): Disposable? {

        return when (id) {
            CATEGORIES.FILMS.id -> swapiService.getFilms()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.sortedBy { it.episodeId }.map { it.title }) },
                            { showError() }
                    )

            CATEGORIES.PEOPLE.id -> swapiService.getPeople()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }) },
                            { showError() }
                    )


            CATEGORIES.PLANETS.id -> swapiService.getPlanets()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }) },
                            { showError() }
                    )

            CATEGORIES.SPACESHIPS.id -> swapiService.getSpaceships()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }) },
                            { showError() }
                    )

            CATEGORIES.VEHICLES.id -> swapiService.getVehicles()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }) },
                            { showError() }
                    )

            CATEGORIES.SPECIES.id -> swapiService.getSpecies()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> setData(result.results.map { it.name }) },
                            { showError() }
                    )

            else -> null
        }
    }

    private fun setData(data: List<String>) {
        visibilityProgressBar(false)
        setAdapter(data)
    }

    private fun setAdapter(data: List<String>) {
        val adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_selectable_list_item,
                android.R.id.text1,
                data)

        val listView = findViewById<ListView>(R.id.lv_list)

        listView.adapter = adapter
        listView.emptyView = findViewById(R.id.tv_empty_list)
    }

    private fun visibilityProgressBar(visible: Boolean) {
        pbLoad?.visibility = if (visible) VISIBLE else GONE
    }

    private fun showError() {
        Toast.makeText(this, getString(R.string.could_not_load_data), LENGTH_LONG).show()
    }
}