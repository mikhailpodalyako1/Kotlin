package com.gmail.kotlinhw23.model.data

import com.gmail.kotlinhw23.model.dto.WeatherDto

fun convertDtoToModel(weatherDto: WeatherDto) : List<Weather>{
    val fact = weatherDto.fact!!
    return listOf(
        Weather(
            getDefaultCity(),
            fact.temp!!,
            fact.feels_like!!,
            fact.condition!!
        )
    )
}