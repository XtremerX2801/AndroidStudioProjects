package com.example.stringapp.Model.MobileInterest

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileInterest(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val mobileInterestData: List<Data>? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("status")
    val status: Boolean? = null
): Parcelable