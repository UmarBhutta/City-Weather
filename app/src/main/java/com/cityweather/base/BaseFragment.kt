package com.cityweather.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dagger.android.AndroidInjection

abstract class BaseFragment<VM:BaseViewModel>(@LayoutRes val layout: Int, viewModelClass: Class<VM>) : Fragment(){

    open fun init() {}

    val viewModel by lazy {
        (activity as? BaseActivity<*>)?.viewModelProviderFactory?.let { ViewModelProvider(this, it).get(viewModelClass) }
    }

    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(activity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layout,container,false)
    }

    open fun refresh() {}

    open fun navigate(action: Int) {
        view?.let { _view ->
            Navigation.findNavController(_view).navigate(action)
        }
    }
}