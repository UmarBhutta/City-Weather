package com.cityweather

import androidx.multidex.MultiDexApplication
import com.cityweather.dependencyInjection.InjectionHelper
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject


class CityWeatherApp : MultiDexApplication(),HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


    override fun onCreate() {
        super.onCreate()

        InjectionHelper.init(this)
        AndroidThreeTen.init(this)


        //handle rxjava error crashes here
        RxJavaPlugins.setErrorHandler { t ->
            val cause = t.cause

            if (cause != null) {
            }

        }
    }

}