package com.cityweather.dependencyInjection.module

import com.cityweather.ui.citiesForecast.CitiesForecast
import com.cityweather.ui.currentLocation.CurrentLocationForecast
import com.cityweather.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment():MainFragment

    @ContributesAndroidInjector
    abstract fun contributeCitiesForecast():CitiesForecast

    @ContributesAndroidInjector
    abstract fun contributeCurrentLocationForeCast():CurrentLocationForecast

}