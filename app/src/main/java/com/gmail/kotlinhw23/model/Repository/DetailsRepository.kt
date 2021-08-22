package com.gmail.kotlinhw23.model.Repository

import com.gmail.kotlinhw23.model.dto.WeatherDto
import javax.security.auth.callback.Callback


interface DetailsRepository  {
    fun getWeatherDetailsFromServer(lat:Double, lon:Double, callback: retrofit2.Callback<WeatherDto>)
}