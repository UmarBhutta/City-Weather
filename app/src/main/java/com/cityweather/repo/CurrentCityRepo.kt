package com.cityweather.repo

import com.cityweather.network.ApiService
import com.cityweather.network.model.CurrentWeatherResponse
import com.cityweather.network.model.ForecastResponse
import com.cityweather.network.model.ListItem
import com.cityweather.utils.Constants
import com.cityweather.utils.DayForecastMapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CurrentCityRepo @Inject constructor(private val api: ApiService){

    fun getCurrentCityForeCast(lat:Double,lon:Double): Observable<List<ListItem>> {
        return Observable.create {

            api.getDaysHoursWeatherForCurrentLocation(lat, lon,Constants.Values.METRIC).enqueue(object :Callback<ForecastResponse>{
                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    it.tryOnError(t)
                }

                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                   if (response.isSuccessful){
                       val forecastList = response.body()?.list.let {
                           DayForecastMapper().mapFrom(it!!)
                       }
                       it.onNext(forecastList)
                       it.onComplete()
                   }else{
                       it.tryOnError(Throwable("Don't Know"))
                   }
                }

            })
        }
    }

    fun getCurrentCityByCoords(lat:Double,lon:Double):Observable<CurrentWeatherResponse>{
        return Observable.create {
            api.getCityWeatherByCoordinates(lat,lon,Constants.Values.METRIC).enqueue(object :Callback<CurrentWeatherResponse>{
                override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                    it.tryOnError(t)
                }

                override fun onResponse(
                    call: Call<CurrentWeatherResponse>,
                    response: Response<CurrentWeatherResponse>
                ) {
                    if (response.isSuccessful){
                        it.onNext(response.body()!!)
                        it.onComplete()
                    }else{
                        it.tryOnError(Throwable("Don't Know"))
                    }
                }

            })
        }

    }

}