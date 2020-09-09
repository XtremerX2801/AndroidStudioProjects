package com.example.stringapp.Model.Register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class MobileRegister(
    val code: Int? = null,
    @SerializedName("data")
    val registerData: Data? = null,

    val message: String? = null,

    val status: Boolean? = null
) : Parcelable