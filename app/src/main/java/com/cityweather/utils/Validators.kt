package com.cityweather.utils

class Validators{
    companion object {

        fun isValidCitiesNames(cities: String): Boolean {
            if (cities.isEmpty())
                return false
            val citiesArr = getCitiesArr(cities).distinct()
            return citiesArr.size in 3..7
        }

        fun getCitiesArr(cities: String): Array<String> {
            return cities.split(",").toTypedArray()
        }

    }
}