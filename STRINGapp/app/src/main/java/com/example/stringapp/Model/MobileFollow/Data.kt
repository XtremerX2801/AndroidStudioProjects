package com.example.stringapp.Model.MobileFollow

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("badge")
    val badge: String?,
    @SerializedName("badgeID")
    val badgeID: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("checkfollow")
    val checkfollow: Boolean?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("currentLocation")
    val currentLocation: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("facebookID")
    val facebookID: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isBlocked")
    val isBlocked: Boolean?,
    @SerializedName("isLoginFirst")
    val isLoginFirst: Boolean?,
    @SerializedName("isSuggestFollower")
    val isSuggestFollower: Int?,
    @SerializedName("isSuperUser")
    val isSuperUser: Boolean?,
    @SerializedName("notificationSettings")
    val notificationSettings: NotificationSettings?,
    @SerializedName("numberOfLogin")
    val numberOfLogin: Int?,
    @SerializedName("photos")
    val photos: List<Photo>?,
    @SerializedName("profilePhoto")
    val profilePhoto: String?,
    @SerializedName("trash")
    val trash: Boolean?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("website")
    val website: String?
): Parcelable