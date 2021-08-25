package com.gmail.kotlinhw23.model.Repository

import com.gmail.kotlinhw23.model.data.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}