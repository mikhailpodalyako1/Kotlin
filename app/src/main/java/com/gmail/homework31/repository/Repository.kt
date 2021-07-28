package com.gmail.homework31.repository

import com.gmail.homework31.data.Weather

open class Repository : IRepository{ //имплементируем IRepository//


    private val weather: List<Weather> = listOf(
            Weather("Moskva", 34),
            Weather("SPB", 34),
            Weather("Chelyabinsk", 22),
            Weather("Sochy", 20),
            Weather("Novosibirsk", 19)
    )//val - имеет только гетер//

    override fun getWeathers(): List<Weather> {
        return weather
    }

}

//создаем интерфейс//
interface IRepository{
    fun getWeathers(): List<Weather>
}

object  RepositorySingle : IRepository{

    private val weather: List<Weather> = listOf(
            Weather("Moskva", 34),
            Weather("SPB", 34),
            Weather("Chelyabinsk", 22),
            Weather("Sochy", 20),
            Weather("Novosibirsk", 19)
    )//val - имеет только гетер//

    override fun getWeathers(): List<Weather> {
        return weather
    }
}
inline fun getRepository(): IRepository{
    return RepositorySingle
}

