package com.gmail.kotlonlesson92

import android.app.Application
import android.content.Context

class Application : Application (){
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
    companion object {
        lateinit var context: Context
    }
}

interface IContextProvider{
    val context: Context
}
object ContextProvider: IContextProvider{
    override val context: Context
    get() = App.context
}