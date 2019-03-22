package com.slogup.sgsgerpstock.data

import android.content.Context
import com.google.gson.JsonElement
import com.slogup.sgsgerpstock.AppManager
import com.slogup.sgsgerpstock.network.RetrofitClient
import com.slogup.sgsgerpstock.network.RetrofitService
import com.slogup.sgsgerpstock.network.RowData
import com.slogup.sgsgerpstock.network.SGError
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

/**
 * Created by jessehj on 22/03/2019.
 */

interface SessionAPI {
    @POST("/apis/v1/account/sessions/me")
    fun requestSession(@Body params: MutableMap<String, String>): Call<RowData<Session>>

    @DELETE("/apis/v1/account/sessions/me")
    fun deleteSession(): Call<JsonElement>

    interface SessionCompletion {
        fun onSuccess()
        fun onError(error: SGError)
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun requestSignIn(
            context: Context,
            email: String,
            password: String,
            completion: SessionCompletion
        ) {
            val sessionApi = RetrofitClient.get().create(SessionAPI::class.java)

            val params = mutableMapOf<String, String>()
            params["aId"] = email
            params["pass"] = password

            RetrofitService<RowData<Session>>().request(
                sessionApi.requestSession(params),
                object : RetrofitService.Completion {
                    override fun onSuccess(token: String?, response: Any?) {
                        token?.let {
                            AppManager.saveToken(context, token)
                        }

                        if (response is RowData<*>) {

                            val user = (response as RowData<Session>).row.user

                            AppManager.user = user
                            completion.onSuccess()
                        }
                    }

                    override fun onError(error: SGError) {
                        completion.onError(error)
                    }
                })
        }

        fun requestSignOut(
            context: Context,
            completion: SessionCompletion
        ) {
            val token = AppManager.loadToken(context)

            val sessionApi = RetrofitClient.get(token = token).create(SessionAPI::class.java)

            RetrofitService<JsonElement>().request(sessionApi.deleteSession(),
                object : RetrofitService.Completion {
                    override fun onSuccess(token: String?, response: Any?) {
                        AppManager.saveToken(context, null)
                        completion.onSuccess()
                    }

                    override fun onError(error: SGError) {
                        completion.onError(error)
                    }
                })
        }
    }
}