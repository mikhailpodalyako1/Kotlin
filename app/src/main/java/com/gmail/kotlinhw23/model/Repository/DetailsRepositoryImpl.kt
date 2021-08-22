package com.gmail.kotlinhw23.model.Repository

import com.gmail.kotlinhw23.model.dto.WeatherDto

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDto>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}