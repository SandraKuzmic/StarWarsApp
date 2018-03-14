package android.sandra.com.starwarsapp.entity

import com.google.gson.annotations.SerializedName

data class Person(
        val name: String,
        @SerializedName("birth_year") val birthYear: String,
        @SerializedName("eye:color") val eyeColor: String,
        val gender: String,
        @SerializedName("hair_color") val hairColor: String
)