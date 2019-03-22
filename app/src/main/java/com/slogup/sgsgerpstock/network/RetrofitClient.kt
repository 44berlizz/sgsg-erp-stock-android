package com.slogup.sgsgerpstock.network

import com.slogup.sgsgerpstock.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jessehj on 21/03/2019.
 */

object RetrofitClient {
    fun get(host: Host = Host.Default, token: String? = null): Retrofit =
        retrofitClient(host.url, token)

    private fun retrofitClient(baseUrl: String, token: String?, vararg interceptors: Interceptor) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient(token, interceptors))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun okHttpClient(token: String?, interceptors: Array<out Interceptor>) =
        OkHttpClient.Builder()
            .addInterceptor(headersInterceptor(token))
            .addInterceptor(loggingInterceptor())
            .apply { interceptors().addAll(interceptors) }
            .build()

    private fun headersInterceptor(token: String?): Interceptor = Interceptor {

        val builder = it.request().newBuilder()
        if (token != null) {
            builder.addHeader("x-auth-token", token)
        }
        builder.addHeader("Content-Type", "application/json")

        it.proceed(builder.build())
    }

    private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

enum class Host(val url: String) {
    Default(BuildConfig.BASE_URL)
}
