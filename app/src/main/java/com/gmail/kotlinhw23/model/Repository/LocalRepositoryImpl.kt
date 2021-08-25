package com.gmail.kotlinhw23.model.Repository

import com.gmail.kotlinhw23.model.data.Weather
import com.gmail.kotlinhw23.model.data.convertWeatherToEntity
import com.gmail.kotlinhw23.model.data.covertHistoryEntityToWeather
import com.gmail.kotlinhw23.room.HistoryDao

class LocalRepositoryImpl (private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return covertHistoryEntityToWeather (localDataSource.all())

    }

    override fun saveEntity(weather: Weather) {
        return localDataSource.insert(convertWeatherToEntity(weather))
    }
}