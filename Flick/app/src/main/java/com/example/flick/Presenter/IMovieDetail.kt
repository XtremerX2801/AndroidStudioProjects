package com.example.flick.Presenter

import com.example.flick.Model.Youtube

interface IMovieDetail {
    interface Presenter {
        fun getTrailer(id: Int?)
    }

    interface View {
        fun setPresenter(presenter: Presenter)
        fun onResponse(trailers: List<Youtube>?)
        fun onFailure()
    }
}