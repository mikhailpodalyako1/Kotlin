package com.gmail.kotlinhw2.vievmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel (private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData()) :
    ViewModel() {


       fun getData(): LiveData<Any>{
           getDataFromLocaleSource()
           return liveDataToObserve
       }
    private fun getDataFromLocaleSource(){
        Thread{
            sleep(2000)
            liveDataToObserve.postValue(Any())
        }.start()
    }
}