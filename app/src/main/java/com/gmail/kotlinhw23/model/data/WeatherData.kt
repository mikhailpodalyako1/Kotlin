package com.gmail.kotlinhw23.model.data

fun getWorldCities()= listOf(
        Weather(City("Stambul", 41.0122, 28.97641), 20, 19),
        Weather(City("Singapur", 1.18, 103.51),32, 30),
        Weather(City("NY", 40.7143, -74.006),19, 16),
        Weather(City("Varshava", 42.2298, 21.0118),19, 15),
        Weather(City("Bucharest", 44.4323, 26.1063),13, 10),
        Weather(City("Berlin", 52.52000, 13.484953),25, 20),
        Weather(City("Washington", 38.9871, -77.03687),14, 10),
        Weather(City("Dubai", 25.0652, 55.1713),37, 40),
        Weather(City("Kiev", 50.4547, 30.5238),15, 14),
        Weather(City("Budapest", 47.4983, 19.0404), 18, 1),
        Weather(City("Tokio", 35.6895, 139.692),20, 10),
        Weather(City("Kair", 30.0345, 31.1458),39, 35),
        Weather(City("Amsterdam", 52.374, 4.88969),25, 20),
        )

fun getRussianCities() = listOf(
        Weather(City("Moskva", 41.0122, 28.97641), 20, 17),
        Weather(City("SPB", 1.18, 103.51),15, 13),
        Weather(City("Ekaterinburg", 40.7143, -74.006),25, 20),
        Weather(City("Chelyabinsk", 42.2298, 21.0118),24, 19),
        Weather(City("UFA", 44.4323, 26.1063),30, 30),
        Weather(City("Samara", 52.52000, 13.484953),29, 25),
        Weather(City("Kazan", 38.9871, -77.03687),),
        Weather(City("Volgograd", 25.0652, 55.1713),),
        Weather(City("Novosibirsk", 50.4547, 30.5238),),
        Weather(City("Nijny-Novgorod", 47.4983, 19.0404),),
        Weather(City("Sochy", 35.6895, 139.692),),
        Weather(City("Krasnodar", 30.0345, 31.1458),32, 27),
    )