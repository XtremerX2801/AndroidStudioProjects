package com.example.flick.Presenter

import com.example.flick.Model.Result

interface IMovieList {
    interface Presenter {
        fun getNowPlaying()
    }

    interface View {
        fun setPresenter(presenter: Presenter)
        fun onResponse(movie: List<Result>?, type: Int?)
        fun onFailure()
    }
}