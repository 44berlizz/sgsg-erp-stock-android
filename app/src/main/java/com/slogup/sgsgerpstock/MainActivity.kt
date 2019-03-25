package com.slogup.sgsgerpstock

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.slogup.sgsgerpstock.data.SessionAPI
import com.slogup.sgsgerpstock.data.User
import com.slogup.sgsgerpstock.data.repository.RoomRepository
import com.slogup.sgsgerpstock.data.repository.SGRepository
import com.slogup.sgsgerpstock.network.SGError
import com.slogup.sgsgerpstock.scene.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import timber.log.Timber

class MainActivity : BaseActivity() {

    private val repository: SGRepository = RoomRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configToolbar(toolbar)

        configureLiveDataObservers()
        configureButtonActions()
    }

    private fun configureButtonActions() {
        signOutButton.setOnClickListener {
            SessionAPI.requestSignOut(object : SessionAPI.SignOutCompletion {
                override fun onSuccess() {
                    repository.clearUser()
                    toast("로그아웃 되었습니다")

                }

                override fun onError(error: SGError) {
                    toast(error.message)
                }
            })
        }

        signInButton.setOnClickListener {
            SessionAPI.requestSignIn("ceo@slogup.com", "Pugols12!@", object : SessionAPI.SignInCompletion {
                override fun onSuccess(user: User) {
                    repository.saveUser(user)
                    toast("로그인 성공")
                }

                override fun onError(error: SGError) {
                    toast("로그인 실패")
                }
            })
        }
    }

    private fun configureLiveDataObservers() {
        repository.getAllUsers().observe(this, Observer {
            it?.let { users ->
                
            }
        })

    }
}




