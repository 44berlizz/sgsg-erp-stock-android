package com.slogup.sgsgerpstock

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.room.Room
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.slogup.sgsgerpstock.data.repository.SGDatabase
import timber.log.Timber

class SGApplication : Application() {

    companion object {
        lateinit var instance: SGApplication
    }

    lateinit var database: SGDatabase

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        PreferenceHolder.setContext(applicationContext)
        database = Room.databaseBuilder(this, SGDatabase::class.java, "sg_database").build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}