package com.example.flick.Api

import com.example.flick.Model.Movie
import com.example.flick.Model.Trailer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDatabaseApi {
    @GET("movie/now_playing")
    fun doGetListNowPlayingMovies(@Query("page") page: Int = 1): Call<Movie>

    @GET("movie/{movie_id}/videos")
    fun doGetTrailer(@Path("movie_id") id: Int?): Call<Trailer>
}