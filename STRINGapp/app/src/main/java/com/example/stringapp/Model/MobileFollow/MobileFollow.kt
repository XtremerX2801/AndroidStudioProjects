package com.example.stringapp.Model.MobileFollow

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileFollow(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val mobileFollowData: List<Data>? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("status")
    val status: Boolean? = null
): Parcelable