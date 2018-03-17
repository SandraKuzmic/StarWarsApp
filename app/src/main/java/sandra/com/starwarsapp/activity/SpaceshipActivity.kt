package sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivitySpaceshipBinding
import android.support.v7.app.AppCompatActivity
import sandra.com.starwarsapp.SPACESHIP_BUNDLE_KEY
import sandra.com.starwarsapp.entity.Spaceship

class SpaceshipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spaceship)

        val spaceship = intent.getSerializableExtra(SPACESHIP_BUNDLE_KEY) as Spaceship

        val mBinding = DataBindingUtil.setContentView<ActivitySpaceshipBinding>(this, R.layout.activity_spaceship)

        populateActivityLayout(spaceship, mBinding)
    }

    private fun populateActivityLayout(spaceship: Spaceship, mBinding: ActivitySpaceshipBinding) {
        mBinding.apply {
            tvSpaceshipName.text = spaceship.name
            tvSpaceshipModel.append(spaceship.model)
            tvSpaceshipStarshipClass.append(spaceship.starshipClass)
            tvSpaceshipManufacturer.append(spaceship.manufacturer)
            tvSpaceshipCostInCredits.text = resources.getString(R.string.cost, spaceship.costInCredits)
            tvSpaceshipLength.text = resources.getString(R.string.length_m, spaceship.length)
            tvSpaceshipCrew.append(spaceship.crew)
            tvSpaceshipPassengers.append(spaceship.passengers)
            tvSpaceshipMaxAtmospheringSpeed.text = resources.getString(R.string.max_speed, spaceship.maxAtmospheringSpeed)
            tvSpaceshipHyperdriveRating.append(spaceship.hyperdriveRating)
            tvSpaceshipMGLT.append(spaceship.MGLT)
            tvSpaceshipCargoCapacity.text = resources.getString(R.string.cargo_capacity, spaceship.cargo_capacity)
            tvSpaceshipConsumables.append(spaceship.consumables)
        }
    }
}
