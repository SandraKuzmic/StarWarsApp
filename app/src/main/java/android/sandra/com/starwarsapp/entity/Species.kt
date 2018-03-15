package android.sandra.com.starwarsapp.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Species(
        @SerializedName("name") val name: String,
        @SerializedName("classification") val classification: String,
        @SerializedName("designation") val designation: String,
        @SerializedName("average_height") val averageHeight: String,
        @SerializedName("average_lifespan") val averageLifespan: String,
        @SerializedName("eye_colors") val eye_colors: String,
        @SerializedName("hair_colors") val hairColors: String,
        @SerializedName("skin_colors") val skinColors: String,
        @SerializedName("language") val language: String,
        @SerializedName("homeworld") val homeworld: String,
        @SerializedName("url") val url: String
) : Serializable
