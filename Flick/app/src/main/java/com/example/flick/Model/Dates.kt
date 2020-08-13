package com.example.flick.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dates(

    @SerializedName("maximum")
    @Expose
    val maximum: String,

    @SerializedName("minimum")
    @Expose
    val minimum: String
)