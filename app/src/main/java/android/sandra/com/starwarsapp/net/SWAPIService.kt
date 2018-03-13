package android.sandra.com.starwarsapp.net

import android.sandra.com.starwarsapp.entity.Model
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//base url must end in /
val baseUrl = "https://swapi.co/api/"

interface SWAPIService {

    @GET("films/1")
    fun getFilm():Observable<Model.Film>

    @GET("films")
    fun getFilms():Observable<List<Model.Film>>

    companion object {
        fun create(): SWAPIService {
            val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create(SWAPIService::class.java)
        }
    }

}