package com.gmail.kotlinhw23.model.Repository

import okhttp3.Call
import javax.security.auth.callback.Callback

private const val REQUEST_API_KEY = "X-Yandex-API-Key"

class RemoteDataSource {
    fun getWeatherDetails(requestLink: String, callback: okhttp3.Callback)

}