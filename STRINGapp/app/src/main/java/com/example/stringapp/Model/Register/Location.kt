package com.example.stringapp.Model.Register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(

    val description: String?,

    val lat: Int?,

    val long: Int?,

    val name: String?,

    val placeID: String?
) : Parcelable