package com.gmail.kotlinhw23.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.kotlinhw23.app.App.Companion.getHistoryDao
import com.gmail.kotlinhw23.model.AppState
import com.gmail.kotlinhw23.model.Repository.LocalRepository
import com.gmail.kotlinhw23.model.Repository.LocalRepositoryImpl

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getAllHistory(){
        historyLiveData.value = AppState.Loading
        historyLiveData.value = AppState.Success(historyRepository.getAllHistory())
    }
}