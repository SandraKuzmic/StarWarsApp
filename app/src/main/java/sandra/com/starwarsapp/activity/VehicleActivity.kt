package sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivityVehicleBinding
import android.support.v7.app.AppCompatActivity
import sandra.com.starwarsapp.VEHICLE_BUNDLE_KEY
import sandra.com.starwarsapp.entity.Vehicle

class VehicleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle)

        val vehicle = intent.getSerializableExtra(VEHICLE_BUNDLE_KEY) as Vehicle

        val mBinding = DataBindingUtil.setContentView<ActivityVehicleBinding>(this, R.layout.activity_vehicle)

        populateActivityLayout(vehicle, mBinding)
    }

    private fun populateActivityLayout(vehicle: Vehicle, mBinding: ActivityVehicleBinding) {
        mBinding.apply {
            tvVehicleName.text = vehicle.name
            tvVehicleModel.append(vehicle.model)
            tvVehicleClass.append(vehicle.vehicleClass)
            tvVehicleManufacturer.append(vehicle.manufacturer)
            tvVehicleCostInCredits.text = resources.getString(R.string.cost, vehicle.costInCredits)
            tvVehicleLength.text = resources.getString(R.string.length_m, vehicle.length)
            tvVehicleCrew.append(vehicle.crew)
            tvVehiclePassengers.append(vehicle.passengers)
            tvVehicleMaxAtmospheringSpeed.text = resources.getString(R.string.max_speed, vehicle.maxAtmospheringSpeed)
            tvVehicleCargoCapacity.text = resources.getString(R.string.cargo_capacity, vehicle.cargoCapacity)
            tvVehicleConsumables.append(vehicle.consumables)
        }
    }
}
