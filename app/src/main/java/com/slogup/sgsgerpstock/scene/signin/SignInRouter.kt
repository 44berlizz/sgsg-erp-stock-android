package com.slogup.sgsgerpstock.scene.signin

import android.content.Intent
import com.slogup.sgsgerpstock.MainActivity
import java.lang.ref.WeakReference


interface SignInRoutingLogic {
    fun navigateToMain()
}

class SignInRouter : SignInRoutingLogic {

    lateinit var activity: WeakReference<SignInActivity>
    lateinit var dataStore: SignInDataStore

    override fun navigateToMain() {
        Intent(activity.get(), MainActivity::class.java).apply {
            activity.get()?.startActivity(this)
        }
    }
}