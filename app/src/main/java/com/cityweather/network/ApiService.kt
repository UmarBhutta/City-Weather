package com.cityweather.network

import com.cityweather.network.model.CurrentWeatherResponse
import com.cityweather.network.model.ForecastResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("weather")
    fun getCityWeather(
        @Query("q") cityName: String,
        @Query("units") units: String
    ): Call<CurrentWeatherResponse>

    @GET("weather")
    fun getCityWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String
    ): Call<CurrentWeatherResponse>

    @GET("forecast")
    fun getDaysHoursWeatherForCurrentLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String
    ): Call<ForecastResponse>

}