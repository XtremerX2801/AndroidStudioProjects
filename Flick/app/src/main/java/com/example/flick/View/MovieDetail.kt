package com.example.flick.View

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.flick.BuildConfig.YOUTUBE_API_KEY
import com.example.flick.Model.Result
import com.example.flick.Model.Youtube
import com.example.flick.Presenter.IMovieDetail
import com.example.flick.Presenter.MovieDetailPresenter
import com.example.flick.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import java.util.*

class MovieDetail: YouTubeBaseActivity(), IMovieDetail.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)

        val data: Result = intent.getSerializableExtra("Movie") as Result

        val trailerTitle: String? = data.title
        val trailerRating: Double? = data.voteAverage
        val trailerReleaseDay: String? = data.releaseDate
        val trailerLanguage: String? = data.originalLanguage
        val trailerOverview: String? = data.overview

        val detailTitle: TextView = findViewById(R.id.detail_title)
        val detailRating: TextView = findViewById(R.id.detail_rating)
        val detailReleaseDay: TextView = findViewById(R.id.detail_release_day)
        val detailLanguage: TextView = findViewById(R.id.detail_original_language)
        val detailOverview: TextView = findViewById(R.id.detail_overview)

        presenter.getVideo(data.id)

        detailTitle.text = trailerTitle
        detailRating.text = "Rating: " + trailerRating.toString()
        detailReleaseDay.text = "Release day: " + trailerReleaseDay
        if (trailerLanguage == "ko") {
            detailLanguage.text = "Language: Korean"
        } else detailLanguage.text = "Language: English"
        detailOverview.text = trailerOverview
    }

    private var presenter: IMovieDetail.Presenter
    init {
        presenter = MovieDetailPresenter(this)
    }

    override fun setPresenter(presenter: IMovieDetail.Presenter) {
        this.presenter = presenter
    }

    override fun onResponse(trailers: List<Youtube>?) {

        if(trailers?.size==0) return

        val youtube: YouTubePlayerView = findViewById(R.id.player)

        val source=trailers?.get(0)?.source

        youtube.initialize(YOUTUBE_API_KEY,
                object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(provider: YouTubePlayer.Provider,
                                                         youTubePlayer: YouTubePlayer, b: Boolean) {
                        youTubePlayer.cueVideo(source)
                    }

                    override fun onInitializationFailure(provider: YouTubePlayer.Provider,
                                                         youTubeInitializationResult: YouTubeInitializationResult) {
                    }
                })
    }

    override fun onFailure() {
        print("Error happens!")
    }

    override fun onBackPressed(){
        val intent = Intent(this@MovieDetail, MainActivity::class.java)
        ContextCompat.startActivity(this@MovieDetail, intent, null)
    }
}