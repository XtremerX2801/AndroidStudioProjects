package com.example.stringapp.Model.Register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WanderlustDetails(

    val isWanderlust: Boolean?,

    val location: Location?
) : Parcelable