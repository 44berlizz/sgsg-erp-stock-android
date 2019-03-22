package com.slogup.sgsgerpstock

import android.os.Bundle
import com.slogup.sgsgerpstock.data.SessionAPI
import com.slogup.sgsgerpstock.network.SGError
import com.slogup.sgsgerpstock.scene.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configToolbar(toolbar)

        configureButtonActions()
    }

    private fun configureButtonActions() {
        signOutButton.setOnClickListener {
            SessionAPI.requestSignOut(this@MainActivity, object: SessionAPI.SessionCompletion {
                override fun onSuccess() {
                    toast("로그아웃 되었습니다")
                    finish()
                }

                override fun onError(error: SGError) {
                    toast(error.message)
                }
            })
        }
    }
}




