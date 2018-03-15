package sandra.com.starwarsapp.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivitySpeciesBinding
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import sandra.com.starwarsapp.PLANET_BUNDLE_KEY
import sandra.com.starwarsapp.SPECIES_BUNDLE_KEY
import sandra.com.starwarsapp.entity.Planet
import sandra.com.starwarsapp.entity.Species
import sandra.com.starwarsapp.net.SWAPIService

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

        disposal = swapiService.getPlanet(getPlanetId(species.homeworld))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            visibilityProgressBar(mBinding.pbLoadSpecies, false)
                            planetOfSpecies = result
                            populateActivityLayout(species, mBinding, result)
                        },
                        { err ->
                            visibilityProgressBar(mBinding.pbLoadSpecies, false)
                            Toast.makeText(this, getString(R.string.could_not_load_data), Toast.LENGTH_LONG).show()

                        }
                )
    }

    override fun onPause() {
        super.onPause()
        disposal?.dispose()
    }

    private fun populateActivityLayout(species: Species, mBinding: ActivitySpeciesBinding, planet: Planet) {
        mBinding.tvSpeciesName.text = species.name
        mBinding.tvSpeciesClassification.append(species.classification)
        mBinding.tvSpeciesDesignation.append(species.designation)
        mBinding.tvSpeciesHomeworld.append(planet.name)
        mBinding.tvSpeciesLanguage.append(species.language)
        mBinding.tvSpeciesAverageLifespan.text = resources.getString(R.string.average_life_span_years, species.averageLifespan)
        mBinding.tvSpeciesAverageHeight.text = resources.getString(R.string.average_height_cm, species.averageHeight)
        mBinding.tvSpeciesSkinColors.append(species.skinColors)
        mBinding.tvSpeciesHairColors.append(species.hairColors)
        mBinding.tvSpeciesEyeColors.append(species.eyeColors)

        mBinding.tvSpeciesHomeworld.setOnClickListener { _ ->
            val intent = Intent(this, PlanetsActivity::class.java)
            intent.putExtra(PLANET_BUNDLE_KEY, planet)

            startActivity(intent)
        }
    }

    private fun getPlanetId(url: String): Int = url.takeLast(3)[1].toInt()

    private fun visibilityProgressBar(pbLoad: ProgressBar, visible: Boolean) {
        pbLoad.visibility = if (visible) View.VISIBLE else View.GONE
    }

}
