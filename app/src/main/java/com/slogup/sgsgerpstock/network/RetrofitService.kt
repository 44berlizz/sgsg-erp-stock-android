package com.slogup.sgsgerpstock.network

import com.slogup.sgsgerpstock.AppManager
import com.slogup.sgsgerpstock.SGConstants
import com.slogup.sgsgerpstock.util.parseError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by jessehj on 21/03/2019.
 */

interface Request<T> {
    fun request(call: Call<T>, completion: RetrofitService.Completion)
}

class RetrofitService<T> : Request<T> {

    interface Completion {
        fun onSuccess(response: Any?)
        fun onError(error: SGError)
    }

    override fun request(call: Call<T>, completion: Completion) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                Timber.e("Retrofit onFailure: ${t.localizedMessage}")
                val errMsg = when (t) {
                    is UnknownHostException -> "인터넷 연결상태를 확인해주세요."
                    is SocketTimeoutException -> "요청시간이 초과되었습니다."
                    else -> t.localizedMessage
                }
                completion.onError(SGError(message = errMsg))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {

                    val token = response.headers()[SGConstants.Network.x_auth_token]
                    AppManager.token = token

                    response.body()?.let {
                        completion.onSuccess(it)
                    } ?: run {
                        completion.onSuccess(null)
                    }
                } else {
                    completion.onError(response.parseError())
                }
            }
        })
    }
}