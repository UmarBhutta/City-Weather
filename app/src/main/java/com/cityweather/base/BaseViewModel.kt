package com.cityweather.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel:ViewModel(){

    val loader: MutableLiveData<Boolean> = MutableLiveData()
    val refreshFromPull: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<Error> = MutableLiveData()
    val empty: MutableLiveData<Boolean> = MutableLiveData()

    var compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()

    }

    public enum class Error {
        NO_ERROR,
        API_ERROR,
        GENERAL_ERROR,
        NETWORK_ERROR
    }
}