package com.miladheydari.snappmarketandroidtest

import androidx.multidex.MultiDexApplication
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : MultiDexApplication() {



    override fun onCreate() {
        super.onCreate()


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Prefs.Builder()
            .setContext(this)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}