package com.example.stringapp.Model.MobileSignIn

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileSignIn(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val mobileSignInData: Data? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Boolean? = null
): Parcelable