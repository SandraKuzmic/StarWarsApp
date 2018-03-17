package sandra.com.starwarsapp.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivitySpeciesBinding
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import sandra.com.starwarsapp.PLANET_BUNDLE_KEY
import sandra.com.starwarsapp.SPECIES_BUNDLE_KEY
import sandra.com.starwarsapp.entity.Planet
import sandra.com.starwarsapp.entity.Species
import sandra.com.starwarsapp.getDataId
import sandra.com.starwarsapp.net.SWAPIService
import sandra.com.starwarsapp.visibilityOfView

class SpeciesActivity : AppCompatActivity() {

    private var disposal: Disposable? = null

    private val swapiService by lazy {
        SWAPIService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)

        val species = intent.getSerializableExtra(SPECIES_BUNDLE_KEY) as Species

        val mBinding = DataBindingUtil.setContentView<ActivitySpeciesBinding>(this, R.layout.activity_species)

        var planetOfSpecies: Planet

        disposal = swapiService.getPlanet(getDataId(species.homeworld))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            visibilityOfView(mBinding.pbLoadSpecies, false)
                            planetOfSpecies = result
                            populateActivityLayout(species, mBinding, result)
                        },
                        { err ->
                            visibilityOfView(mBinding.pbLoadSpecies, false)
                            visibilityOfView(mBinding.speciesView, false)
                            Toast.makeText(this, getString(R.string.could_not_load_data), Toast.LENGTH_LONG).show()
                        }
                )
    }

    override fun onPause() {
        super.onPause()
        disposal?.dispose()
    }

    private fun populateActivityLayout(species: Species, mBinding: ActivitySpeciesBinding, planet: Planet) {
        mBinding.apply {
            tvSpeciesName.text = species.name
            tvSpeciesClassification.append(species.classification)
            tvSpeciesDesignation.append(species.designation)
            tvSpeciesHomeworld.append(planet.name)
            tvSpeciesLanguage.append(species.language)
            tvSpeciesAverageLifespan.text = resources.getString(R.string.average_life_span_years, species.averageLifespan)
            tvSpeciesAverageHeight.text = resources.getString(R.string.average_height_cm, species.averageHeight)
            tvSpeciesSkinColors.append(species.skinColors)
            tvSpeciesHairColors.append(species.hairColors)
            tvSpeciesEyeColors.append(species.eyeColors)

            tvSpeciesHomeworld.setOnClickListener { _ ->
                val intent = Intent(this@SpeciesActivity, PlanetsActivity::class.java)
                intent.putExtra(PLANET_BUNDLE_KEY, planet)
                startActivity(intent)
            }
        }
    }
}
