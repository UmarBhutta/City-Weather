package com.cityweather.ui.currentLocation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.cityweather.base.BaseViewModel
import com.cityweather.network.model.CurrentWeatherResponse
import com.cityweather.repo.CurrentCityRepo
import com.cityweather.base.LocationLiveData
import com.cityweather.base.LocationModel
import com.cityweather.network.model.ForecastResponse
import com.cityweather.network.model.ListItem
import com.cityweather.utils.GpsUtils
import com.cityweather.utils.NetworkConnectionException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrentLocationForecastViewModel @Inject constructor(private val context: Context,private val currentCityRepo: CurrentCityRepo) : BaseViewModel() {

    private val locationData = LocationLiveData(context)
    val gpsEnable: MutableLiveData<Boolean> =  MutableLiveData()
    val foreCastData: MutableLiveData<List<ListItem>> = MutableLiveData()
    val currentCityWeather: MutableLiveData<CurrentWeatherResponse> = MutableLiveData()

    fun getLocationData() = locationData

    fun startCheckingForLocation(){

        GpsUtils(context).turnGPSOn(object : GpsUtils.OnGpsListener{
            override fun gpsStatus(isGPSEnable: Boolean) {
                gpsEnable.value = true
            }

        })
    }

    fun fetchForecastForCurrentLocation(locationModel: LocationModel) {
        loader.value = true
        compositeDisposable.add(currentCityRepo.getCurrentCityByCoords(locationModel.latitude,locationModel.longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loader. value = false
                if (it != null){
                    currentCityWeather.value = it
                }else{
                    empty.value = true
                }

            },{
                loader. value = false

                if (it is NetworkConnectionException)
                error.value = Error.NETWORK_ERROR

                error.value = Error.API_ERROR
            }))


        compositeDisposable.add(currentCityRepo.getCurrentCityForeCast(locationModel.latitude,locationModel.longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                foreCastData.value = it
            }
        )

    }




}
