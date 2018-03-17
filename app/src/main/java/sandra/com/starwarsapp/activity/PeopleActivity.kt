package sandra.com.starwarsapp.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivityPeopleBinding
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import sandra.com.starwarsapp.PEOPLE_BUNDLE_KEY
import sandra.com.starwarsapp.PLANET_BUNDLE_KEY
import sandra.com.starwarsapp.entity.Person
import sandra.com.starwarsapp.entity.Planet
import sandra.com.starwarsapp.getDataId
import sandra.com.starwarsapp.net.SWAPIService
import sandra.com.starwarsapp.visibilityOfView

class PeopleActivity : AppCompatActivity() {

    private var disposal: Disposable? = null

    private val swapiService by lazy {
        SWAPIService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        val person = intent.getSerializableExtra(PEOPLE_BUNDLE_KEY) as Person

        val mBinding = DataBindingUtil.setContentView<ActivityPeopleBinding>(this, R.layout.activity_people)

        disposal = swapiService.getPlanet(getDataId(person.homeworld))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            visibilityOfView(mBinding.pbLoadHomeworld, false)
                            populateActivityLayout(person, mBinding, result)
                        },
                        { err ->
                            visibilityOfView(mBinding.pbLoadHomeworld, false)
                            visibilityOfView(mBinding.peopleView, false)
                            Toast.makeText(this, getString(R.string.could_not_load_data), Toast.LENGTH_LONG).show()
                        }
                )
    }

    override fun onPause() {
        super.onPause()
        disposal?.dispose()
    }

    private fun populateActivityLayout(person: Person, mBinding: ActivityPeopleBinding, planet: Planet) {
        mBinding.apply {
            tvPersonName.text = person.name
            tvPersonGender.append(person.gender)
            tvPersonBirthYear.append(person.birthYear)
            tvPersonHomeworld.append(planet.name)
            tvPersonHeight.text = resources.getString(R.string.height_cm, person.height)
            tvPersonMass.text = resources.getString(R.string.mass_kg, person.mass)
            tvPersonSkinColor.append(person.skinColor)
            tvPersonHairColor.append(person.hairColor)
            tvPersonEyeColor.append(person.eyeColor)

            tvPersonHomeworld.setOnClickListener { _ ->
                val intent = Intent(this@PeopleActivity, PlanetsActivity::class.java)
                intent.putExtra(PLANET_BUNDLE_KEY, planet)
                startActivity(intent)
            }
        }
    }
}
