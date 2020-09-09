package com.example.flick.Presenter

import com.example.flick.Model.Youtube

interface IMovieDetail {
    interface Presenter {
        fun getVideo(id: Int?)
    }

    interface View {
        fun setPresenter(presenter: Presenter)
        fun onResponse(trailers: List<Youtube>?)
        fun onFailure()
    }
}