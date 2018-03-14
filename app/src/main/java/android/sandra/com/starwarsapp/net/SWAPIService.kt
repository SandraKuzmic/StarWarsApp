package android.sandra.com.starwarsapp.net

import android.sandra.com.starwarsapp.entity.Model
import android.sandra.com.starwarsapp.entity.Result
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//base url must end in /
val baseUrl = "https://swapi.co/api/"

interface SWAPIService {

    @GET("films/{id}")
    fun getFilm(@Path("id") id: Int): Observable<Model.Film>

    @GET("films")
    fun getFilms(): Observable<Result<Model.Film>>

    @GET("people")
    fun getPeople(): Observable<Result<Model.Person>>

    @GET("planets")
    fun getPlanets(): Observable<Result<Model.Planet>>

    @GET("starships")
    fun getSpaceships(): Observable<Result<Model.Spaceship>>

    @GET("vehicles")
    fun getVehicles(): Observable<Result<Model.Vehicle>>

    @GET("species")
    fun getSpecies(): Observable<Result<Model.Species>>


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