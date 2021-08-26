package com.gmail.kotlinhw23.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.kotlinhw23.app.App.Companion.getHistoryDao
import com.gmail.kotlinhw23.model.AppState
import com.gmail.kotlinhw23.model.Repository.*
import com.gmail.kotlinhw23.model.data.Weather
import com.gmail.kotlinhw23.model.data.convertDtoToModel
import com.gmail.kotlinhw23.model.dto.FactDto
import com.gmail.kotlinhw23.model.dto.WeatherDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.IOException

private const val SERVER_ERROR = "Server error"
private const val REQUEST_ERROR = "Server request error"
private const val CORRUPTED_DATA = "Data is not full"

class DetailsViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource()),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        detailsLiveData.value = AppState.Loading
        detailsRepository.getWeatherDetailsFromServer(lat, lon, callBack)
    }

    fun saveCityToDB(weather: Weather){
        historyRepository.saveEntity(weather)
    }

    private val callBack = object : Callback<WeatherDto> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<WeatherDto>, response: Response<WeatherDto>) {
            val serverResponse: WeatherDto? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<WeatherDto>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    fun checkResponse(serverResponse: WeatherDto): AppState {
        val fact: FactDto? = serverResponse.fact
        return if (fact?.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToModel(serverResponse))
        }
    }
}