package sandra.com.starwarsapp.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Person(
        @SerializedName("name") val name: String,
        @SerializedName("birth_year") val birthYear: String,
        @SerializedName("eye_color") val eyeColor: String,
        @SerializedName("gender") val gender: String,
        @SerializedName("hair_color") val hairColor: String,
        @SerializedName("height") val height: String,
        @SerializedName("mass") val mass: String,
        @SerializedName("skin_color") val skinColor: String,
        @SerializedName("homeworld") val homeworld: String,
        @SerializedName("url") val url: String
) : Serializable