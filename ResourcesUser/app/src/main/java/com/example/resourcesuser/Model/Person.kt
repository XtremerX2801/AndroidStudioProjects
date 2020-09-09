package com.example.resourcesuser.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(

    val firstname: String,

    val lastname: String,

    val middlename: String,
    //////////////////////////

    val organization: String,

    val qualifier: String,
    ////////////////////////

    val rank: Int,

    val role: String,

    val title: String
    ////////////////////////
) : Parcelable