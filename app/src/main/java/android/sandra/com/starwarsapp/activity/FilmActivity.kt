package android.sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.FILM_BUNDLE_KEY
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivityFilmBinding
import android.sandra.com.starwarsapp.entity.Film
import android.support.v7.app.AppCompatActivity

class FilmActivity : AppCompatActivity() {

    var mBinding: ActivityFilmBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        val film = intent.getSerializableExtra(FILM_BUNDLE_KEY) as Film

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_film)

        populateActivityLayout(film.title)
    }

    private fun populateActivityLayout(title: String) {
        mBinding?.tvFilmTitle?.text = title
    }
}
