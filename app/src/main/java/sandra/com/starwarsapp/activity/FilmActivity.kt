package sandra.com.starwarsapp.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.sandra.com.starwarsapp.databinding.ActivityFilmBinding
import android.support.v7.app.AppCompatActivity
import sandra.com.starwarsapp.FILM_BUNDLE_KEY
import sandra.com.starwarsapp.entity.Film

class FilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        val film = intent.getSerializableExtra(FILM_BUNDLE_KEY) as Film

        val mBinding = DataBindingUtil.setContentView<ActivityFilmBinding>(this, R.layout.activity_film)

        populateActivityLayout(film, mBinding)
    }

    private fun populateActivityLayout(film: Film, mBinding: ActivityFilmBinding) {
        mBinding.tvFilmTitle.text = film.title
        mBinding.tvFilmEpisode.append(episodeId(film.episodeId))
        mBinding.tvFilmOpeningCrawl.text = film.openingCrawl
        mBinding.tvFilmDirector.append(film.director)
        mBinding.tvFilmProducer.append(film.producer)
        mBinding.tvFilmDate.append(film.releaseDate)
    }

    private fun episodeId(id: Int) = resources.getStringArray(R.array.roman_numbers)[id]
}
