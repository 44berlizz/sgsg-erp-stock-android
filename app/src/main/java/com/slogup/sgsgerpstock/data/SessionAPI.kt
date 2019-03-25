package com.slogup.sgsgerpstock.data

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.slogup.sgsgerpstock.data.repository.RoomRepository
import com.slogup.sgsgerpstock.data.repository.SGRepository
import com.slogup.sgsgerpstock.network.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by jessehj on 22/03/2019.
 */

interface SessionAPI {
    @POST("/apis/v1/account/sessions/me")
    fun requestSession(@Body params: MutableMap<String, String>): Call<RowData<Session>>

    @DELETE("/apis/v1/account/sessions/me")
    fun deleteSession(): Call<JsonElement>

    interface SignInCompletion {
        fun onSuccess(user: User)
        fun onError(error: SGError)
    }

    interface SignOutCompletion {
        fun onSuccess()
        fun onError(error: SGError)
    }

    companion object {
        fun requestSignIn(
            email: String,
            password: String,
            completion: SignInCompletion
        ) {
            val sessionApi = UnauthenticatedRetrofit.get().create(SessionAPI::class.java)

            val params = mutableMapOf<String, String>()
            params["aId"] = email
            params["pass"] = password

            RetrofitService<RowData<Session>>().request(
                sessionApi.requestSession(params),
                object : RetrofitService.Completion {
                    override fun onSuccess(response: Any?) {
                        if (response is RowData<*> && (response as RowData<Session>).row.user != null) {
                            val user = response.row.user!!
                            completion.onSuccess(user)
                        } else {
                            completion.onError(SGError(message = "Unexpected Error"))
                        }
                    }

                    override fun onError(error: SGError) {
                        completion.onError(error)
                    }
                })
        }

        fun requestSignOut(
            completion: SignOutCompletion
        ) {
            val sessionApi = OAuthRetrofit.get().create(SessionAPI::class.java)

            RetrofitService<JsonElement>().request(sessionApi.deleteSession(),
                object : RetrofitService.Completion {
                    override fun onSuccess(response: Any?) {
                        completion.onSuccess()
                    }

                    override fun onError(error: SGError) {
                        completion.onError(error)
                    }
                })
        }
    }
}

data class Session(
    @SerializedName("token")
    var token: String? = "",
    @SerializedName("user")
    var user: User?
)