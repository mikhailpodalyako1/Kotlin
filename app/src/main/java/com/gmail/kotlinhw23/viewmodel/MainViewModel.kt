package com.gmail.kotlinhw23.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel
    : ViewModel() {

    private val liveDataToObserver: MutableLiveData<String> = MutableLiveData()

    private var counter:Int = 0

        fun getData():LiveData<String>{
            return liveDataToObserver
        }

    fun requestData(data: String){
        Thread{
            sleep(2000)
            counter++
            liveDataToObserver.postValue(data + counter)
        }.start()
    }

    // TODO: Implement the ViewModel
}