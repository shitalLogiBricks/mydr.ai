package com.app.mydrai

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin


class ShiftApplication : Application() {

    init {
        shiftApplication = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@ShiftApplication)
            androidFileProperties()
        }
        injectFeature()


    }

    companion object {
        lateinit var shiftApplication: ShiftApplication
            private set

        val applicationContext: Context
            get() {
                return shiftApplication.applicationContext
            }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //LocalizationManager.onAttach(this)
    }

    fun hasNetwork(): Boolean {
        return isNetworkAvailable()
    }

    private fun isNetworkAvailable(): Boolean {
        var isConnected = false
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}