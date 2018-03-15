package android.sandra.com.starwarsapp.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Vehicle(
        @SerializedName("name") val name: String,
        @SerializedName("model") val model: String,
        @SerializedName("vehicle_class") val vehicleClass: String,
        @SerializedName("manufacturer") val manufacturer: String,
        @SerializedName("length") val length: String,
        @SerializedName("cost_in_credits") val costInCredits: String,
        @SerializedName("crew") val crew: String,
        @SerializedName("passengers") val passengers: String,
        @SerializedName("max_atmosphering_speed") val maxAtmospheringSpeed: String,
        @SerializedName("cargo_capacity") val cargoCapacity: String,
        @SerializedName("consumables") val consumables: String,
        @SerializedName("url") val url: String
) : Serializable