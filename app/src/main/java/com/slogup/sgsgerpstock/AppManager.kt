package com.slogup.sgsgerpstock

import android.content.Context
import android.preference.PreferenceManager
import com.slogup.sgsgerpstock.data.User

/**
 * Created by jessehj on 22/03/2019.
 */

object AppManager {
    private const val USER_TOKEN = "userToken"

    var user: User? = null

    fun loadToken(context: Context): String? {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreference.getString(USER_TOKEN, null)
    }

    fun saveToken(context: Context, token: String?) {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreference.edit().putString(USER_TOKEN, token).apply()
    }
}