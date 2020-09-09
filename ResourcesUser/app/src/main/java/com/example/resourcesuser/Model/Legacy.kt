package com.example.resourcesuser.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Legacy(

    val thumbnail: String,

    val thumbnailheight: Int,

    val thumbnailwidth: Int,

    val wide: String,

    val wideheight: Int,

    val widewidth: Int,

    val xlarge: String,

    val xlargeheight: Int,

    val xlargewidth: Int
): Parcelable