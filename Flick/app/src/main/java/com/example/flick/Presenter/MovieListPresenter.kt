package com.example.flick.Presenter

import com.example.flick.Api.api
import com.example.flick.Model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListPresenter(var view: IMovieList.View): IMovieList.Presenter{

    override fun getNowPlaying(page: Int) {
        api.createService().doGetListNowPlayingMovies(page).enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                println(t.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.body() != null) {
                    view.onResponse(response.body()?.results, 1)

                }
            }

        })
    }

}