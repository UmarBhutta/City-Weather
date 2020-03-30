package com.cityweather.dependencyInjection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.cityweather.utils.NetworkConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConnectionInterceptor @Inject constructor(private val applicationContext: Context):Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NetworkConnectionException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        var res = false
        connectivityManager.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    res = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            } else {
               it.activeNetworkInfo.apply {
                   res = this !=null && this.isConnected
               }
            }
        }
        return res
    }

}