package com.slogup.sgsgerpstock

import android.content.Context
import android.preference.PreferenceManager
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.slogup.sgsgerpstock.data.User

/**
 * Created by jessehj on 22/03/2019.
 */

object AppManager: PreferenceHolder() {
    var token: String? by bindToPreferenceFieldNullable()
}