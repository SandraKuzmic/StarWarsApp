package android.sandra.com.starwarsapp.entity

import com.google.gson.annotations.SerializedName

object Model {

    data class Film(
            val title: String,
            @SerializedName("episode_id") val episodeId: Int,
            @SerializedName("opening_crawl") val openingCrawl: String,
            val director: String,
            val producer: String,
            @SerializedName("release_date") val releaseDate: String,
            val url: String,
            val created: String,
            val edited: String
    )

    data class Person(
            val name: String,
            @SerializedName("birth_year") val birthYear: String,
            @SerializedName("eye:color") val eyeColor: String,
            val gender: String,
            @SerializedName("hair_color") val hairColor: String
    )

    data class Planet(
            val name: String
    )

    data class Species (
            val name: String
    )

    data class Spaceship (
            val name: String
    )

    data class Vehicle(
            val name: String
    )

}
