package com.example.flick.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Youtube: Serializable{
    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("size")
    @Expose
    val size: String? = null

    @SerializedName("source")
    @Expose
    val source: String? = null

    @SerializedName("type")
    @Expose
    val type: String? = null
}