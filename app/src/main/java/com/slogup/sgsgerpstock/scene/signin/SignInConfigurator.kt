package com.slogup.sgsgerpstock.scene.signin

import java.lang.ref.WeakReference


object SignInConfigurator {

    fun configure(activity: SignInActivity) {

        val router = SignInRouter()
        router.activity = WeakReference(activity)

        val presenter = SignInPresenter()
        presenter.activity = WeakReference(activity)

        val interactor = SignInInteractor()
        interactor.presenter = presenter

        activity.interactor = interactor
        activity.router = router
        activity.router.dataStore = interactor
    }
}