package com.slogup.sgsgerpstock.scene.signin

import android.os.Bundle
import com.slogup.sgsgerpstock.R
import com.slogup.sgsgerpstock.scene.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.toast


interface SignInDisplayLogic {
    fun displayError(errMsg: String)
    fun displayProgress()
    fun dismissProgress()
    fun routeToMain()
    fun displayMessage(message: String)
}

class SignInActivity : BaseActivity(), SignInDisplayLogic {

    lateinit var interactor: SignInBusinessLogic
    lateinit var router: SignInRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SignInConfigurator.configure(this)
        setContentView(R.layout.activity_sign_in)

        configToolbar(toolbar, homeButtonEnabled = false)
        configureButtonAction()
    }

    fun configureButtonAction() {
        signInButton.setOnClickListener {
            SignIn.SignInRequest(this@SignInActivity, emailEditText.text.toString(), passwordEditText.text.toString()).apply {
                interactor.fetchSessionData(this)
            }
        }
    }

    fun fetchSignInData() {

    }

    override fun displayError(errMsg: String) {
        displayMessage(errMsg)
    }

    override fun displayProgress() {
        displayProgress()
    }

    override fun dismissProgress() {
        dismissProgress()
    }

    override fun routeToMain() {
        router.navigateToMain()
    }

    override fun displayMessage(message: String) {
        toast(message)
    }
}