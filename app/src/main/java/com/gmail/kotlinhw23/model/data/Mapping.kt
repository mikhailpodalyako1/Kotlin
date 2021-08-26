package com.gmail.kotlinhw23.model.data

import com.gmail.kotlinhw23.model.dto.WeatherDto
import com.gmail.kotlinhw23.room.HistoryEntity

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

fun covertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
    return entityList.map {
        Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
    }
}
    fun convertWeatherToEntity(weather: Weather): HistoryEntity{
        return HistoryEntity(0, weather.city.city, weather.temperature, weather.condition)
    }

