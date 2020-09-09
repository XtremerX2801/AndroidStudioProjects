package com.example.stringapp.Model.MobileFollow

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("url")
    val url: Url?
): Parcelable