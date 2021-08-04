package com.gmail.kotlinhw23.model

import com.gmail.kotlinhw23.model.data.Weather

sealed class AppState{
     data class Success(val weatherData: Weather) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}
