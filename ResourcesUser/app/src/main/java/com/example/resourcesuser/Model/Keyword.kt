package com.example.resourcesuser.Model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Keyword(

    @SerializedName("major")
    @Expose
    val major: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("rank")
    @Expose
    val rank: Int,

    @SerializedName("value")
    @Expose
    val value: String
): Parcelable