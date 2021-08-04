package com.gmail.kotlinhw23.model.Repository

import com.gmail.kotlinhw23.model.data.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()

    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather()
    }
}