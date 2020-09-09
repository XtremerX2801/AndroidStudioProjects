package com.example.myapplicationss.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(

    val docs: List<Doc>,

    val meta: Meta
): Parcelable