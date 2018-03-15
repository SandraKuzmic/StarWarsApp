package android.sandra.com.starwarsapp.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Spaceship(
        @SerializedName("name") val name: String,
        @SerializedName("model") val model: String,
        @SerializedName("starship_class") val starshipClass: String,
        @SerializedName("manufacturer") val manufacturer: String,
        @SerializedName("cost_in_credits") val costInCredits: String,
        @SerializedName("length") val length: String,
        @SerializedName("crew") val crew: String,
        @SerializedName("passengers") val passengers: String,
        @SerializedName("max_atmosphering_speed") val maxAtmospheringSpeed: String,
        @SerializedName("hyperdrive_rating") val hyperdriveRating: String,
        @SerializedName("MGLT") val MGLT: String,
        @SerializedName("cargo_capacity") val cargo_capacity: String,
        @SerializedName("consumables") val consumables: String,
        @SerializedName("url") val url: String
) : Serializable
