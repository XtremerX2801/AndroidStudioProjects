package com.example.stringapp.Model.Register

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("access_token")
    val accessToken: String?,

    val badgeID: Int?,

    val bio: String?,
    @SerializedName("created_at")
    val createdAt: String?,

    val currentLocation: String?,

    val email: String?,

    val facebookID: String?,

    val gender: String?,

    val id: Int?,

    val isActive: Boolean?,

    val isSuperUser: Boolean?,

    val notificationSettings: NotificationSettings?,

    val profilePhoto: String?,
    @SerializedName("refresh_token")
    val refreshToken: String?,

    val trash: Boolean?,

    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,

    val username: String?,
    @SerializedName("wanderlust_details")
    val wanderlustDetails: WanderlustDetails?,

    val website: String?
) : Parcelable