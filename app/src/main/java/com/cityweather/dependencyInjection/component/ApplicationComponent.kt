package com.cityweather.dependencyInjection.component

import android.app.Application
import com.cityweather.CityWeatherApp
import com.cityweather.dependencyInjection.module.ActivityModule
import com.cityweather.dependencyInjection.module.ApplicationModule
import com.cityweather.dependencyInjection.module.NetworkModule
import com.cityweather.dependencyInjection.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    NetworkModule::class,
    ActivityModule::class,
    ViewModelModule::class
])

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(cityWeatherApp: CityWeatherApp)
}