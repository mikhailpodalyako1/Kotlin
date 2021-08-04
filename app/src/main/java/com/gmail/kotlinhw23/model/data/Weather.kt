package com.gmail.kotlinhw23.model.data

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = -5
)

fun getDefaultCity () = City("Moskva", 55.7522, 37.36  )