package android.sandra.com.starwarsapp

const val CATEGORY_BUNDLE_KEY = "CATEGORY_BUNDLE_KEY"
const val CATEGORY_BUNDLE_DEFAULT = 0

enum class CATEGORIES(val id: Int) {
    FILMS(0),
    PEOPLE(1),
    PLANETS(2),
    SPACESHIPS(3),
    VEHICLES(4),
    SPECIES(5)
}