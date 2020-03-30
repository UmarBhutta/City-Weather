package com.cityweather.ui.currentLocation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager

import com.cityweather.R
import com.cityweather.base.BaseFragment
import com.cityweather.dependencyInjection.InjectAble
import com.cityweather.base.LocationModel
import com.cityweather.network.model.CurrentWeatherResponse
import com.cityweather.utils.Constants
import kotlinx.android.synthetic.main.current_location_forecast_fragment.*
import kotlinx.android.synthetic.main.today_forecast.*

class CurrentLocationForecast : BaseFragment<CurrentLocationForecastViewModel>(R.layout.current_location_forecast_fragment,CurrentLocationForecastViewModel::class.java),
    InjectAble {


    private var isGPSEnabled = false
    private var isLocationReceived = false
    private val foreCastAdapter = ForeCastAdapter()

    companion object {
        fun newInstance() = CurrentLocationForecast()
    }

    override fun init() {
        super.init()
        startToListenGPSAvailability()
        invokeLocationAction()
        subscribeToLoading()
        subscribeToError()
        viewModel?.startCheckingForLocation()
        subscribeToCurrentLocationData()
        subscribeToNext5Days()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            Navigation.findNavController(view).navigateUp()
        }
        SetupForecastRecycler()
    }

    private fun SetupForecastRecycler(){
        forecast_recycler.apply {
            adapter = foreCastAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.Values.GPS_REQUEST) {
                isGPSEnabled = true
                invokeLocationAction()
            }
        }
    }

    private fun invokeLocationAction() {
        when {
            !isGPSEnabled -> {
                //latLong.text = getString(R.string.enable_gps)
                //startLocationUpdate()
            }

            isPermissionsGranted() -> startLocationUpdate()

            shouldShowRequestPermissionRationale() -> {
                //latLong.text = getString(R.string.permission_request)
            }

            else -> requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                Constants.Values.LOCATION_REQUEST
            )
        }
    }

    private fun subscribeToCurrentLocationData(){
        viewModel?.currentCityWeather?.observe(viewLifecycleOwner, Observer {
            updateCityData(it)
        })
    }


    fun updateCityData(data:CurrentWeatherResponse){
        temperature.text = data.main?.getTempString()
        description.text = data.weather?.first()?.getDescriptionText()
        maxTempp.text = "Max Temp. : " + data.main?.getTempMaxString()
        windSpeed.text ="Wind Speed : " +data.wind?.speed.toString()
        mainContainer.visibility = View.VISIBLE
    }


    private fun subscribeToNext5Days(){
        viewModel?.foreCastData?.observe(viewLifecycleOwner, Observer {
            foreCastAdapter.addItems(it)
        })
    }

    private fun startToListenGPSAvailability(){
        viewModel?.gpsEnable?.observe(viewLifecycleOwner, Observer {
            isGPSEnabled = true
            invokeLocationAction()
        })
    }


    private fun startLocationUpdate() {
        viewModel?.getLocationData()?.observe(viewLifecycleOwner, Observer {

            if(isLocationReceived){
                //don't update it back
            }else{
                isLocationReceived = true
                startLoadingForeCastForCurrentLocation(it)
            }


        })

    }

    private fun subscribeToLoading(){
        viewModel?.loader?.observe(viewLifecycleOwner, Observer {
            if (it){
                showLoader()
            }else{
                hideLoader()
            }
        })
    }

    private fun subscribeToError(){
        viewModel?.error?.observe(viewLifecycleOwner, Observer {

        })
    }

    fun showLoader(){
        loader?.visibility = View.VISIBLE
    }

    fun hideLoader(){
        loader?.visibility = View.GONE
    }

    fun showErrorView(){

    }

    fun hideErrorView(){

    }

    private fun startLoadingForeCastForCurrentLocation(locationModel: LocationModel){
        viewModel?.fetchForecastForCurrentLocation(locationModel)
    }

    private fun isPermissionsGranted() =
        ActivityCompat.checkSelfPermission(
            activity!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowRequestPermissionRationale() =
        ActivityCompat.shouldShowRequestPermissionRationale(
            activity!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) && ActivityCompat.shouldShowRequestPermissionRationale(
            activity!!,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.Values.LOCATION_REQUEST -> {
                invokeLocationAction()
            }
        }
    }


}
