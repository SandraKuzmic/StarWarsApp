package android.sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.VEHICLE_BUNDLE_KEY
import android.sandra.com.starwarsapp.databinding.ActivityVehicleBinding
import android.sandra.com.starwarsapp.entity.Vehicle
import android.support.v7.app.AppCompatActivity

class VehicleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle)

        val vehicle = intent.getSerializableExtra(VEHICLE_BUNDLE_KEY) as Vehicle

        val mBinding = DataBindingUtil.setContentView<ActivityVehicleBinding>(this, R.layout.activity_vehicle)

        populateActivityLayout(vehicle, mBinding)
    }

    private fun populateActivityLayout(vehicle: Vehicle, mBinding: ActivityVehicleBinding) {
        mBinding.tvVehicleName.text = vehicle.name
        mBinding.tvVehicleModel.append(vehicle.model)
        mBinding.tvVehicleClass.append(vehicle.vehicleClass)
        mBinding.tvVehicleManufacturer.append(vehicle.manufacturer)
        mBinding.tvVehicleCostInCredits.text = resources.getString(R.string.cost, vehicle.costInCredits)
        mBinding.tvVehicleLength.text = resources.getString(R.string.length_m)
        mBinding.tvVehicleCrew.append(vehicle.crew)
        mBinding.tvVehiclePassengers.append(vehicle.passengers)
        mBinding.tvVehicleMaxAtmospheringSpeed.text = resources.getString(R.string.max_speed, vehicle.maxAtmospheringSpeed)
        mBinding.tvVehicleCargoCapacity.text = resources.getString(R.string.cargo_capacity, vehicle.cargoCapacity)
        mBinding.tvVehicleConsumables.append(vehicle.consumables)
    }
}
