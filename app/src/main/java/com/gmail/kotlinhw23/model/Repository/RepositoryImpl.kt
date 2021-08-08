package com.gmail.kotlinhw23.model.Repository

import com.gmail.kotlinhw23.model.data.Weather
import com.gmail.kotlinhw23.model.data.getRussianCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld()= getRussianCities()


}