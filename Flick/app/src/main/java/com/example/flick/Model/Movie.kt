package com.example.flick.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Movie {

    @SerializedName("dates")
    @Expose
    val dates: Dates? = null

    @SerializedName("page")
    @Expose
    val page: Int? = null

    @SerializedName("results")
    @Expose
    val results: List<Result>? = null

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null
}