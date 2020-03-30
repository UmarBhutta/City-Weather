package com.cityweather.dependencyInjection.module

import com.cityweather.dependencyInjection.scope.ActivityScope
import com.cityweather.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{


    //contribute fragment for main activity
    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun mainActivity(): MainActivity
}