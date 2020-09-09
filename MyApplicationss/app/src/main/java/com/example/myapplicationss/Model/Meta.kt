package com.example.myapplicationss.Model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Meta(
    @Expose
    val hits: Int,

    @Expose
    val offset: Int,

    @Expose
    val time: Int
): Parcelable