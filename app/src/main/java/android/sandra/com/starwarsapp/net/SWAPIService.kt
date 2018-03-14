package android.sandra.com.starwarsapp.net

import android.sandra.com.starwarsapp.entity.*
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//base url must end in /
val baseUrl = "https://swapi.co/api/"

interface SWAPIService {

    @GET("films/{id}")
    fun getFilm(@Path("id") id: Int): Observable<Film>

    @GET("films/")
    fun getFilms(@Query("page") page: Int = 1): Observable<Result<Film>>

    @GET("people/")
    fun getPeople(@Query("page") page: Int = 1): Observable<Result<Person>>

    @GET("planets/")
    fun getPlanets(@Query("page") page: Int = 1): Observable<Result<Planet>>

    @GET("starships/")
    fun getSpaceships(@Query("page") page: Int = 1): Observable<Result<Spaceship>>

    @GET("vehicles/")
    fun getVehicles(@Query("page") page: Int = 1): Observable<Result<Vehicle>>

    @GET("species/")
    fun getSpecies(@Query("page") page: Int = 1): Observable<Result<Species>>


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