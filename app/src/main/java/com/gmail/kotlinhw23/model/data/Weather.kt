package com.gmail.kotlinhw23.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = -0,
    val condition: String = "Sunny"

): Parcelable

fun getDefaultCity () = City("Moskva", 55.7522, 37.36  )