package com.gmail.kotlinhw23.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.kotlinhw23.model.AppState
import com.gmail.kotlinhw23.model.Repository.Repository
import com.gmail.kotlinhw23.model.Repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val repository : Repository = RepositoryImpl())
    : ViewModel() {

    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData()

    private var counter:Int = 0

        fun getData():LiveData<AppState>{
            return liveDataToObserver
        }
    fun getWeatherFromLocaleSource() {
        liveDataToObserver.value = AppState.Loading
        Thread{
            sleep(1000)
            counter++
            liveDataToObserver.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
        }.start()
    }

    fun getWeatherFromRemoteSourse(){
        liveDataToObserver.value = AppState.Loading
        Thread{
            sleep(2000)
            counter++
            liveDataToObserver.postValue(AppState.Success(repository.getWeatherFromServer()))
        }.start()
    }
}