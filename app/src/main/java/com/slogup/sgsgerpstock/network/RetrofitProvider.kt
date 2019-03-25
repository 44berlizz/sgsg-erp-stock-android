package com.slogup.sgsgerpstock.network

/**
 * Created by jessehj on 22/03/2019.
 */

abstract class RetrofitProvider<T>(val initializer: () -> T) {
    var override: T? = null
    var original: T? = null

    fun get(): T = override ?: original ?: initializer().apply { original = this }
//    fun lazyGet(): Lazy<T> = lazy { get() }
}