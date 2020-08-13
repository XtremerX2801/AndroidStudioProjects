package com.example.flick.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Trailer {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("quicktime")
    @Expose
    val quicktime: List<Any>? = null

    @SerializedName("youtube")
    @Expose
    val youtube: List<Youtube>? = null
}