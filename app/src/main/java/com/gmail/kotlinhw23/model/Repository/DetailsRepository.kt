package com.gmail.kotlinhw23.model.Repository

import javax.security.auth.callback.Callback

interface DetailsRepository  {
    fun getWeatherDetailsFromServer(requestLinl: String, callback: Callback)
}