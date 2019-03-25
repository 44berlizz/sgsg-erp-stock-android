package com.slogup.sgsgerpstock.scene.signin

import com.slogup.sgsgerpstock.R
import com.slogup.sgsgerpstock.data.SessionAPI
import com.slogup.sgsgerpstock.data.User
import com.slogup.sgsgerpstock.data.repository.RoomRepository
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
    private val repository: RoomRepository = RoomRepository()

    override fun fetchSessionData(request: SignIn.SignInRequest) {

        request.email.validateMessage(ValidateField.Email)?.let {
            presenter.presentError(it)
            return
        }

        request.password.validateMessage(ValidateField.Password)?.let {
            presenter.presentError(it)
            return
        }

        // request  ->
        SessionAPI.requestSignIn(
            request.email,
            request.password,
            object : SessionAPI.SignInCompletion {
                override fun onSuccess(user: User) {
                    repository.saveUser(user)
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
}