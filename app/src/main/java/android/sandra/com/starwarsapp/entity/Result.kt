package android.sandra.com.starwarsapp.entity

data class Result<out T>(
        val count: Int,
        val next: String,
        val results: List<T>
)