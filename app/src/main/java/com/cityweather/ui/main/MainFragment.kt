package com.cityweather.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.cityweather.R
import com.cityweather.base.BaseFragment
import com.cityweather.dependencyInjection.InjectAble
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment<MainViewModel>(R.layout.main_fragment,MainViewModel::class.java),InjectAble {



    override fun init() {
        super.init()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButton.setOnClickListener {
                navigate(R.id.action_mainFragment_to_citiesFragment)
        }

        currentLocationButton.setOnClickListener {
            //load
            navigate(R.id.action_mainFragment_to_currentLocationFragment)
        }
    }

}
