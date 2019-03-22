package com.slogup.sgsgerpstock.network

import com.google.gson.annotations.SerializedName
import com.slogup.sgsgerpstock.SGConstants

/**
 * Created by jessehj on 21/03/2019.
 */

data class SGError(
    var errorCode: Int? = null,
    var message: String = "",
    var errorParams: MutableList<ErrorParam>? = null
)

data class ErrorParam(
    @SerializedName(SGConstants.Error.domain)
    val domain: String? = null,
    @SerializedName(SGConstants.Error.code)
    val code: String? = null,
    @SerializedName(SGConstants.Error.msg)
    val msg: String? = null,
    @SerializedName(SGConstants.Error.param)
    val param: String? = null
)
