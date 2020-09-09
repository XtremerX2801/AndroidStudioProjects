package com.example.stringapp.Model.MobileSignIn

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationSettings(
    @SerializedName("comments")
    val comments: String?,
    @SerializedName("likes")
    val likes: String?,
    @SerializedName("new_followes")
    val newFollowes: String?,
    @SerializedName("post_saves")
    val postSaves: String?,
    @SerializedName("string")
    val string: String?
): Parcelable