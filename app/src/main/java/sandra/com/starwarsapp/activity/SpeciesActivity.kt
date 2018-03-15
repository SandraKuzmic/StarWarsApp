package android.sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivitySpeciesBinding
import android.sandra.com.starwarsapp.entity.Species
import android.support.v7.app.AppCompatActivity
import sandra.com.starwarsapp.SPECIES_BUNDLE_KEY

class SpeciesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)

        val species = intent.getSerializableExtra(SPECIES_BUNDLE_KEY) as Species

        val mBinding = DataBindingUtil.setContentView<ActivitySpeciesBinding>(this, R.layout.activity_species)

        populateActivityLayout(species, mBinding)
    }

    private fun populateActivityLayout(species: Species, mBinding: ActivitySpeciesBinding) {
        mBinding.tvSpeciesName.text = species.name
        mBinding.tvSpeciesClassification.append(species.classification)
        mBinding.tvSpeciesDesignation.append(species.designation)
        mBinding.tvSpeciesHomeworld.append(species.homeworld)
        mBinding.tvSpeciesLanguage.append(species.language)
        mBinding.tvSpeciesAverageLifespan.text = resources.getString(R.string.average_life_span_years, species.averageLifespan)
        mBinding.tvSpeciesAverageHeight.text = resources.getString(R.string.average_height_cm, species.averageHeight)
        mBinding.tvSpeciesSkinColors.append(species.skinColors)
        mBinding.tvSpeciesHairColors.append(species.hairColors)
        mBinding.tvSpeciesEyeColors.append(species.eyeColors)
    }

}