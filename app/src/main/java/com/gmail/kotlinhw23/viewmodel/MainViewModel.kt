package com.gmail.kotlinhw23.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.kotlinhw23.model.AppState
import com.gmail.kotlinhw23.model.Repository.Repository
import com.gmail.kotlinhw23.model.Repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository = RepositoryImpl()) :
    ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve
    fun getWeatherFromLocaleSourceRus() = getDataFromLocaleSource(isRussia = true)

    fun getWeatherFromLocaleSourceWorld() = getDataFromLocaleSource(isRussia = false)
    private fun getDataFromLocaleSource(isRussia: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(3000)
            liveDataToObserve.postValue(
                AppState.Success(
                    if (isRussia) repository.getWeatherFromLocalStorageRus()
                    else repository.getWeatherFromLocalStorageWorld()
                )
            )
        }.start()
    }
}