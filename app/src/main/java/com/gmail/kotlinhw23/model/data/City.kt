package com.gmail.kotlinhw23.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City (
    val city: String,
    val lat: Double,
    var lon: Double
    ) : Parcelable