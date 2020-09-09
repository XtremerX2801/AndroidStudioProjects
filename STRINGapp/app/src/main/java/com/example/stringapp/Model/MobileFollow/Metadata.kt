package com.example.stringapp.Model.MobileFollow

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Metadata(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("current_per_page")
    val currentPerPage: String?,
    @SerializedName("next_page")
    val nextPage: Int?,
    @SerializedName("prev_pages")
    val prevPages: Int?,
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
): Parcelable