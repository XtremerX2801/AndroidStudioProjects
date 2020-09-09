package com.example.stringapp.Model.FollowUser


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
    @SerializedName("currentLocation")
    val currentLocation: String?,
    @SerializedName("date_of_birth")
    val dateOfBirth: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("facebookID")
    val facebookID: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isLoginFirst")
    val isLoginFirst: Boolean?,
    @SerializedName("isNewUser")
    val isNewUser: Boolean?,
    @SerializedName("isSuperUser")
    val isSuperUser: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("notificationSettings")
    val notificationSettings: NotificationSettings?,
    @SerializedName("numberOfLogin")
    val numberOfLogin: Int?,
    @SerializedName("profilePhoto")
    val profilePhoto: String?,
    @SerializedName("trash")
    val trash: Boolean?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("wanderlust_details")
    val wanderlustDetails: String?,
    @SerializedName("website")
    val website: String?
): Parcelable