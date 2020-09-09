package com.example.stringapp.Model.MobileFollow

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationSettings(
    @SerializedName("app_update")
    val appUpdate: String?,
    @SerializedName("comments")
    val comments: String?,
    @SerializedName("contact_joins_string")
    val contactJoinsString: String?,
    @SerializedName("likes")
    val likes: String?,
    @SerializedName("near_you")
    val nearYou: String?,
    @SerializedName("new_followes")
    val newFollowes: String?,
    @SerializedName("post_saves")
    val postSaves: String?,
    @SerializedName("processor")
    val processor: String?,
    @SerializedName("string")
    val string: String?
): Parcelable