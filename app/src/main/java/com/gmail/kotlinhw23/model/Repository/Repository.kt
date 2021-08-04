package com.gmail.kotlinhw23.model.Repository

import com.gmail.kotlinhw23.model.data.Weather

interface Repository {
    fun getWeatherFromServer() : Weather
    fun getWeatherFromLocalStorage() : Weather
}