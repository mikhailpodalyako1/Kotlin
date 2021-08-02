package com.gmail.kotlinhw23.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel (private val liveDataToObserver: MutableLiveData<Any> = MutableLiveData())
    : ViewModel() {

        fun getData():LiveData<Any>{
            getDataFromLocaleSourse()
            return liveDataToObserver
        }

    private fun getDataFromLocaleSourse(){
        Thread{
            sleep(2000)
            liveDataToObserver.postValue(Any())
        }.start()
    }

    // TODO: Implement the ViewModel
}