package sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivityPlanetsBinding
import android.support.v7.app.AppCompatActivity
import sandra.com.starwarsapp.PLANET_BUNDLE_KEY
import sandra.com.starwarsapp.entity.Planet

class PlanetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planets)

        val planet = intent.getSerializableExtra(PLANET_BUNDLE_KEY) as Planet

        val mBinding = DataBindingUtil.setContentView<ActivityPlanetsBinding>(this, R.layout.activity_planets)

        populateActivityLayout(planet, mBinding)
    }

    private fun populateActivityLayout(planet: Planet, mBinding: ActivityPlanetsBinding) {
        mBinding.apply {
            tvPlanetName.text = planet.name
            tvPlanetDiameter.text = resources.getString(R.string.diameter_km, planet.diameter)
            tvPlanetRotationPeriod.text = resources.getString(R.string.rotation_period_hours, planet.rotationPeriod)
            tvPlanetOrbitalPeriod.text = resources.getString(R.string.orbital_period_hours, planet.orbitalPeriod)
            tvPlanetGravity.text = resources.getString(R.string.gravity_g, planet.gravity)
            tvPlanetPopulation.append(planet.population)
            tvPlanetClimate.append(planet.climate)
            tvPlanetTerrain.append(planet.terrain)
            tvPlanetSurfaceWater.text = resources.getString(R.string.percentage_water, planet.surfaceWater)
        }
    }
}
