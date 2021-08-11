package com.gmail.kotlinhw23.view

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.gmail.kotlinhw23.BuildConfig
import com.gmail.kotlinhw23.model.dto.WeatherDto
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors


class WeatherLoader (
    private val listener: WeatherLoaderListener,
    private val lat: Double,
    private val lon: Double
            ){

    interface WeatherLoaderListener{
        fun onLoaded(weatherDto: WeatherDto)
        fun onFinal(throwable: Throwable)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadWeather(){
        try {
            val uri =
                URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
            val handler = Handler(Looper.myLooper()!!)
            Thread(Runnable {
                lateinit var urlConnection: HttpURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                        "X-Yandex-API-Key",
                        BuildConfig.WEATHER_API_KEY
                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val weatherDto: WeatherDto =
                        Gson().fromJson(getLines(bufferedReader), WeatherDto::class.java)
                    handler.post { listener.onLoaded(weatherDto) }
                } catch (e: Exception) {
                    Log.e("WEATHER", "Fail connection", e)
                    e.printStackTrace()
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("WEATHER", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader):String{
        return reader.lines().collect(Collectors.joining("\n"))
    }
}