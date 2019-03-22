package com.slogup.sgsgerpstock

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import timber.log.Timber

class SgsgApplication : Application() {

    companion object {
        lateinit var instance: SgsgApplication
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}