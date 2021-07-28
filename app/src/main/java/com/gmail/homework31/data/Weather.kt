package com.gmail.homework31.data

val weather = Weather("SPB", 25)

data class Weather (val town: String = "My Town", val temperature: Int = 10) {

    constructor( town: String, temperature: Int, preassure: Int) : this(town, temperature){
        this.preassure = preassure

    }

    constructor(humidity: Int) : this() {

    }

    var preassure: Int = 0
    get(){
        registrator("get")
        return field
    }
    set(value){
        registrator("before set")
        field = value
        registrator("after set")
    }

    fun registrator(vararg string: String ):Unit {

        val a = if(string[0] == "get"){
            "get"
        }else{
            "set"
        }

        for ((index, myVar) in string.withIndex()){

        }

        for (i in string.indices) {

        }

        for (s in string) {
        }
    }
}