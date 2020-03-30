package com.cityweather.ui.citiesForecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cityweather.base.BaseViewModel
import com.cityweather.network.model.CurrentWeatherResponse
import com.cityweather.network.model.ForecastResponse
import com.cityweather.repo.ForeCastRepo
import com.cityweather.utils.NetworkConnectionException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CitiesForecastViewModel @Inject constructor(private val foreCastRepo: ForeCastRepo) : BaseViewModel() {

    val daysForeCastData: MutableLiveData<CurrentWeatherResponse> = MutableLiveData()


    fun fetchForeCastForCityName(cityName:String){
        loader.value = true
        compositeDisposable.add(foreCastRepo.getForecastByName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loader. value = false
                if (it != null){
                    daysForeCastData.value = it
                }else{
                    empty.value = true
                }

            },{
                if (it is NetworkConnectionException)
                    error.value = Error.NETWORK_ERROR

                error.value = Error.API_ERROR
            }))

    }

}
