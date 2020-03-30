package com.cityweather.repo

import com.cityweather.network.ApiService
import com.cityweather.network.model.CurrentWeatherResponse
import com.cityweather.network.model.ForecastResponse
import com.cityweather.utils.Constants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class ForeCastRepo @Inject constructor(private val api: ApiService) {

    fun getForecastByName(cityName:String): Observable<CurrentWeatherResponse> {
        return Observable.create {
            api.getCityWeather(cityName,Constants.Values.METRIC).enqueue(object :Callback<CurrentWeatherResponse>{
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