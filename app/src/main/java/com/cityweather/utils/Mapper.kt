package com.cityweather.utils

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}