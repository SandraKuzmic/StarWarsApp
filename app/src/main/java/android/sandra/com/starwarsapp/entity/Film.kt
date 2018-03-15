package android.sandra.com.starwarsapp.entity

import com.google.gson.annotations.SerializedName

//use serialized name everywhere?
data class Film(
        @SerializedName("title") val title: String,
        @SerializedName("episode_id") val episodeId: Int,
        @SerializedName("opening_crawl") val openingCrawl: String,
        @SerializedName("director") val director: String,
        @SerializedName("producer") val producer: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("url") val url: String
)