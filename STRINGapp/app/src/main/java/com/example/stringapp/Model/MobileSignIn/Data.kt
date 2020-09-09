package com.example.stringapp.Model.MobileSignIn

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("badge")
    val badge: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("currentLocation")
    val currentLocation: String?,
    @SerializedName("date_of_birth")
    val dateOfBirth: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("facebookID")
    val facebookID: String?,
    @SerializedName("fcm_token")
    val fcmToken: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isActive")
    val isActive: Boolean?,
    @SerializedName("is_first_time")
    val isFirstTime: Boolean?,
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
    @SerializedName("refresh_token")
    val refreshToken: String?,
    @SerializedName("trash")
    val trash: Boolean?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("wanderlust_details")
    val wanderlustDetails: String?,
    @SerializedName("website")
    val website: String?
): Parcelable