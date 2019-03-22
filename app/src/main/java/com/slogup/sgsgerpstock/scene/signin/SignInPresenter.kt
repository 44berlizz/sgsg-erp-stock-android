package com.slogup.sgsgerpstock.scene.signin

import java.lang.ref.WeakReference


interface SignInPresentationLogic {
    fun routeToMain()
    fun presentError(errMsg: String)
    fun presentMessage(message: String)
}

class SignInPresenter : SignInPresentationLogic {

    lateinit var activity: WeakReference<SignInDisplayLogic>

    override fun routeToMain() {
        activity.get()?.routeToMain()
    }

    override fun presentError(errMsg: String) {
        activity.get()?.displayError(errMsg)
    }

    override fun presentMessage(message: String) {
        activity.get()?.displayMessage(message)
    }
}