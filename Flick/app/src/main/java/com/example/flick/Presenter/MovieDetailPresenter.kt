package com.example.flick.Presenter

import com.example.flick.Api.api
import com.example.flick.Model.Trailer
import com.example.flick.Model.Youtube
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailPresenter(val view: IMovieDetail.View): IMovieDetail.Presenter{

    override fun getTrailer(id: Int?) {
        api.createService().doGetTrailer(id).enqueue(object : Callback<Trailer> {
            override fun onFailure(call: Call<Trailer>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Trailer>?, response: Response<Trailer>) {
                if (response.body() != null) {
                    view.onResponse(response.body()?.youtube as List<Youtube>)
                }
            }
        })
    }

}