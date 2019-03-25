package com.slogup.sgsgerpstock.network

import com.slogup.sgsgerpstock.AppManager
import com.slogup.sgsgerpstock.AppManager.token
import com.slogup.sgsgerpstock.BuildConfig
import com.slogup.sgsgerpstock.SGConstants
import com.slogup.sgsgerpstock.util.addConverters
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by jessehj on 21/03/2019.
 */

object OAuthRetrofit : RetrofitProvider<Retrofit>({
    retrofitClient(accessTokenProvidingInterceptor())
})

object UnauthenticatedRetrofit : RetrofitProvider<Retrofit>({
    retrofitClient()
})

private fun retrofitClient(vararg interceptors: Interceptor) =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient(interceptors))
        .addConverters()
        .build()

private fun okHttpClient(interceptors: Array<out Interceptor>) =
    OkHttpClient.Builder()
        .connectTimeout(SGConstants.Value.connect_timeout_limit, TimeUnit.SECONDS)
        .readTimeout(SGConstants.Value.read_timeout_limit, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .addInterceptor(loggingInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .build()

private fun accessTokenProvidingInterceptor() = Interceptor {
    val builder = it.request().newBuilder()
    AppManager.token?.let { token ->
        builder.addHeader(SGConstants.Network.x_auth_token, token)
    }
    it.proceed(builder.build())
}

private fun headersInterceptor(): Interceptor = Interceptor {
    it.proceed(
        it.request().newBuilder()
            .addHeader(SGConstants.Network.content_type, SGConstants.Network.application_json)
            .build()
    )
}

private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
}

//enum class Host(val url: String) {
//    Default(BuildConfig.BASE_URL)
//}
