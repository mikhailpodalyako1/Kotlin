package com.gmail.kotlinhw23.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel (private val liveDataToObserver: MutableLiveData<Any> = MutableLiveData())
    : ViewModel() {

        fun getData():LiveData<Any>{
            return liveDataToObserver
        }

    // TODO: Implement the ViewModel
}