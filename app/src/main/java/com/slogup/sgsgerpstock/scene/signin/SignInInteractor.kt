package com.slogup.sgsgerpstock.scene.signin

import com.slogup.sgsgerpstock.R
import com.slogup.sgsgerpstock.data.SessionAPI
import com.slogup.sgsgerpstock.network.SGError
import com.slogup.sgsgerpstock.util.ValidateField
import com.slogup.sgsgerpstock.util.validateMessage


interface SignInBusinessLogic : SignInDataPassing, SignInDataStore {
    fun fetchSessionData(request: SignIn.SignInRequest)
}

interface SignInDataPassing {
    // fun setPassedData(Obj: Any)
}

interface SignInDataStore {
    // fun getData(): Any
}

class SignInInteractor : SignInBusinessLogic {

    lateinit var presenter: SignInPresentationLogic
    private var worker = SignInWorker()

    override fun fetchSessionData(request: SignIn.SignInRequest) {
        getEmailValidateMessage(request.email)?.let {
            presenter.presentError(it)
            return
        }

        getPasswordValidateMessage(request.password)?.let {
            presenter.presentError(it)
            return
        }

        // request  ->
        SessionAPI.requestSignIn(
            request.context,
            request.email,
            request.password,
            object : SessionAPI.SessionCompletion {
                override fun onSuccess() {
                    presenter.presentMessage(request.context.getString(R.string.msg_success_sign_in))
                    presenter.routeToMain()
                }

                override fun onError(error: SGError) {
                    if (error.errorParams != null) {
                        error.errorParams!![0].msg?.let {
                            presenter.presentError(it)
                        } ?: run {
                            presenter.presentError(error.message)
                        }

                    } else {
                        presenter.presentError(error.message)
                    }
                }
            })

    }

    private fun getEmailValidateMessage(email: String): String? =
        email.validateMessage(ValidateField.Email)

    private fun getPasswordValidateMessage(password: String): String? =
        password.validateMessage(ValidateField.Password)
}