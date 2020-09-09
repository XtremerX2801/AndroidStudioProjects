package com.example.stringapp.Model.FollowUser


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FollowUser(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val followUserData: Data? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Boolean? = null
): Parcelable