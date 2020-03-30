package com.cityweather.ui.citiesForecast

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.cityweather.R
import com.cityweather.base.BaseFragment
import com.cityweather.dependencyInjection.InjectAble
import com.cityweather.network.model.CurrentWeatherResponse
import com.cityweather.network.model.ForecastResponse
import com.cityweather.utils.Validators
import kotlinx.android.synthetic.main.cities_forecast_fragment.*

class CitiesForecast : BaseFragment<CitiesForecastViewModel>(R.layout.cities_forecast_fragment,CitiesForecastViewModel::class.java),InjectAble {

    companion object {
        fun newInstance() = CitiesForecast()
    }


    val citiesAdapter = CityForeCastAdapter()


    override fun init() {
        super.init()
        subscribeToLoading()
        subscribeToError()
        subscribeToForeCastForCities()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this){
            Navigation.findNavController(view).navigateUp()
        }

        button.setOnClickListener {
            if (Validators.isValidCitiesNames(city_input_layout.editText?.text.toString())){
                //calling view model here
                val listOfCities = Validators.getCitiesArr(city_input_layout.editText?.text.toString())
                listOfCities.forEach {
                    viewModel?.fetchForeCastForCityName(it)
                }

            }else{
                city_input_layout.isErrorEnabled = true
                city_input_layout.error = "Please Enter a valid input"
            }
        }

        city_input_layout.editText?.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(city_input_layout.isErrorEnabled){
                    city_input_layout.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        setUpRecycler()

    }


    private fun setUpRecycler(){

        city_forecast_rv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = citiesAdapter
        }



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
            Toast.makeText(activity,"Error Happen",Toast.LENGTH_SHORT).show()
        })
    }

    private fun subscribeToForeCastForCities(){
        viewModel?.daysForeCastData?.observe(viewLifecycleOwner, Observer {
            if (it == null){

            }else{

                addDataToForeCastList(it)
            }
        })
    }

    private fun addDataToForeCastList(item:CurrentWeatherResponse){
        if (input_container.visibility == View.VISIBLE){
            input_container.visibility = View.GONE
        }

        if (city_forecast_rv.visibility == View.GONE){
            city_forecast_rv.visibility = View.VISIBLE
        }

        citiesAdapter.addItemToList(item)
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


}
