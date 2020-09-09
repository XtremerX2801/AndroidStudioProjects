package com.example.resourcesuser.Model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Byline(
    @SerializedName("organization")
    @Expose
    val organization: String,

    @SerializedName("original")
    @Expose
    val original: String,

    @SerializedName("person")
    @Expose
    val person: List<Person>
): Parcelable