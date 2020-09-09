package com.example.myapplicationss.Model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Multimedia(

    val caption: String?,

    val credit: String?,

    @SerializedName("crop_name")
    @Expose
    val cropName: String?,

    val height: Int?,

    val legacy: Legacy?,

    val rank: Int?,

    val subType: String?,

    val subtype: String?,

    val type: String?,

    val url: String?,

    val width: Int?
    ): Parcelable