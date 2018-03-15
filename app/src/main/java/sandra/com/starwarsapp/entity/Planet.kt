package sandra.com.starwarsapp.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Planet(
        @SerializedName("name") val name: String,
        @SerializedName("diameter") val diameter: String,
        @SerializedName("rotation_period") val rotationPeriod: String,
        @SerializedName("orbital_period") val orbitalPeriod: String,
        @SerializedName("gravity") val gravity: String,
        @SerializedName("population") val population: String,
        @SerializedName("climate") val climate: String,
        @SerializedName("terrain") val terrain: String,
        @SerializedName("surface_water") val surfaceWater: String,
        @SerializedName("url") val url: String
) : Serializable