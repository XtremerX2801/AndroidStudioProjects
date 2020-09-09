package com.example.resourcesuser.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsArticle(
    val copyright: String? = "",
    val response: Response? = null,
    val status: String? = ""
) : Parcelable