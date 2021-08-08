package com.gmail.kotlinhw23.model.Repository

import com.gmail.kotlinhw23.model.data.Weather
import com.gmail.kotlinhw23.model.data.getRussianCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()

    }

    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getRussianCities()
    }

}