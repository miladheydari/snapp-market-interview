package com.miladheydari.snappmarketandroidtest.utils

import android.content.Context
import com.miladheydari.snappmarketandroidtest.utils.extensions.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context?
) : Interceptor {

    private val applicationContext = context?.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (applicationContext?.isNetworkAvailable() == false)
            throw NoInternetException("no internet")
        return chain.proceed(chain.request())
    }

}