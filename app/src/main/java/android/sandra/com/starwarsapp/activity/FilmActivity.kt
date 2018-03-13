package android.sandra.com.starwarsapp.activity

import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.net.SWAPIService
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FilmActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val swapiService by lazy {
        SWAPIService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        disposable = swapiService.getFilm()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result -> Log.d("SW", result.title)},
                        {err -> Log.d("SW", err.message)}
                )

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}