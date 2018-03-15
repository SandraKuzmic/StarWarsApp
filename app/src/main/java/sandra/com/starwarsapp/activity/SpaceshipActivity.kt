package android.sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import sandra.com.starwarsapp.SPACESHIP_BUNDLE_KEY
import android.sandra.com.starwarsapp.databinding.ActivitySpaceshipBinding
import android.sandra.com.starwarsapp.entity.Spaceship
import android.support.v7.app.AppCompatActivity

class SpaceshipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spaceship)

        val spaceship = intent.getSerializableExtra(SPACESHIP_BUNDLE_KEY) as Spaceship

        val mBinding = DataBindingUtil.setContentView<ActivitySpaceshipBinding>(this, R.layout.activity_spaceship)

        populateActivityLayout(spaceship, mBinding)
    }

    private fun populateActivityLayout(spaceship: Spaceship, mBinding: ActivitySpaceshipBinding) {
        mBinding.tvSpaceshipName.text = spaceship.name
        mBinding.tvSpaceshipModel.append(spaceship.model)
        mBinding.tvSpaceshipStarshipClass.append(spaceship.starshipClass)
        mBinding.tvSpaceshipManufacturer.append(spaceship.manufacturer)
        mBinding.tvSpaceshipCostInCredits.text = resources.getString(R.string.cost, spaceship.costInCredits)
        mBinding.tvSpaceshipLength.text = resources.getString(R.string.length_m, spaceship.length)
        mBinding.tvSpaceshipCrew.append(spaceship.crew)
        mBinding.tvSpaceshipPassengers.append(spaceship.passengers)
        mBinding.tvSpaceshipMaxAtmospheringSpeed.text = resources.getString(R.string.max_speed, spaceship.maxAtmospheringSpeed)
        mBinding.tvSpaceshipHyperdriveRating.append(spaceship.hyperdriveRating)
        mBinding.tvSpaceshipMGLT.append(spaceship.MGLT)
        mBinding.tvSpaceshipCargoCapacity.text = resources.getString(R.string.cargo_capacity, spaceship.cargo_capacity)
        mBinding.tvSpaceshipConsumables.append(spaceship.consumables)

    }
}
