package com.example.resourcesuser.Model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Headline(

    val contentKicker: String?,
/////////////////

    val kicker: String?,
//////////////////

    val main: String,

    val name: String?,
//////////////////
    @SerializedName("print_headline")
    @Expose
    val printHeadline: String?,

    val seo: String?,
////////////////////

    val sub: String?
///////////////////
): Parcelable