package com.cityweather.dependencyInjection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cityweather.dependencyInjection.ViewModelFactory
import com.cityweather.dependencyInjection.scope.ViewModelKey
import com.cityweather.ui.MainActivityViewModel
import com.cityweather.ui.citiesForecast.CitiesForecastViewModel
import com.cityweather.ui.currentLocation.CurrentLocationForecast
import com.cityweather.ui.currentLocation.CurrentLocationForecastViewModel
import com.cityweather.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainActivityViewModel(mainActivityViewModel: MainActivityViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel):ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(CitiesForecastViewModel::class)
    abstract fun provideCitiesViewModel(citiesForecastViewModel: CitiesForecastViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CurrentLocationForecastViewModel::class)
    abstract fun provideCurrentLocationViewModel(currentLocationForecastViewModel: CurrentLocationForecastViewModel):ViewModel

}