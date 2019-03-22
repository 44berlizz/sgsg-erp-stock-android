package com.slogup.sgsgerpstock.scene.signin

import android.content.Context


class SignIn {

    data class SignInRequest(
        val context: Context,
        val email: String,
        val password: String
    )
}
