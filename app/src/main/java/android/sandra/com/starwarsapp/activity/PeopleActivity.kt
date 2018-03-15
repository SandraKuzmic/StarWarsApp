package android.sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.PEOPLE_BUNDLE_KEY
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivityPeopleBinding
import android.sandra.com.starwarsapp.entity.Person
import android.support.v7.app.AppCompatActivity

class PeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        val person = intent.getSerializableExtra(PEOPLE_BUNDLE_KEY) as Person

        val mBinding = DataBindingUtil.setContentView<ActivityPeopleBinding>(this, R.layout.activity_people)

        populateActivityLayout(person, mBinding)
    }

    private fun populateActivityLayout(person: Person, mBinding: ActivityPeopleBinding) {
        mBinding.tvPersonName.text = person.name
        mBinding.tvPersonGender.append(person.gender)
        mBinding.tvPersonBirthYear.append(person.birthYear)
        mBinding.tvPersonHeight.text = resources.getString(R.string.height_cm, person.height)
        mBinding.tvPersonMass.text = resources.getString(R.string.mass_kg, person.mass)
        mBinding.tvPersonSkinColor.append(person.skinColor)
        mBinding.tvPersonHairColor.append(person.hairColor)
        mBinding.tvPersonEyeColor.append(person.eyeColor)
    }
}
