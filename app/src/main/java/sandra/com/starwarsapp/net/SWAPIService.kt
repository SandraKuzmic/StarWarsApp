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

    //---------------------films-------------------------------------
    @GET("films/{id}")
    fun getFilm(@Path("id") id: Int): Observable<Film>

    @GET("films/")
    fun getFilms(@Query("page") page: Int = 1): Observable<Result<Film>>


    //---------------------people------------------------------------
    @GET("people/{id}")
    fun getPerson(@Path("id") id:Int): Observable<Person>

    @GET("people/")
    fun getPeople(@Query("page") page: Int = 1): Observable<Result<Person>>


    //---------------------planets-----------------------------------
    @GET("planets/{id}")
    fun getPlanet(@Path("id") id:Int): Observable<Planet>

    @GET("planets/")
    fun getPlanets(@Query("page") page: Int = 1): Observable<Result<Planet>>


    //---------------------spaceships-----------------------------------
    @GET("starships/{id}")
    fun getSpaceship(@Path("id") id:Int): Observable<Spaceship>

    @GET("starships/")
    fun getSpaceships(@Query("page") page: Int = 1): Observable<Result<Spaceship>>


    //---------------------vehicles------------------------------------
    @GET("vehicles/{id}")
    fun getVehicle(@Path("id") id:Int): Observable<Vehicle>

    @GET("vehicles/")
    fun getVehicles(@Query("page") page: Int = 1): Observable<Result<Vehicle>>


    //---------------------species------------------------------------
    @GET("species/{id}")
    fun getSpecie(@Path("id") id:Int): Observable<Species>

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