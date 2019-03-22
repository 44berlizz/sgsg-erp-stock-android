package com.slogup.sgsgerpstock.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slogup.sgsgerpstock.network.ErrorParam
import com.slogup.sgsgerpstock.network.RowsData
import com.slogup.sgsgerpstock.network.SGError
import retrofit2.Response

/**
 * Created by jessehj on 21/03/2019.
 */

fun Response<*>.parseError(): SGError {
    var errorParams: MutableList<ErrorParam>? = null
    errorBody()?.let {
        errorParams = it.string().parse<RowsData<ErrorParam>>()?.rows
    }

    return SGError(code(), message(), errorParams)
}

inline fun <reified T> String.parse(): T? {
    return Gson().fromJson<T>(this, object : TypeToken<T>() {}.type)
}

fun String.validateMessage(validateField: ValidateField): String? {
    return when (validateField) {
        ValidateField.Email -> {
            if (ValidateUtils.isValidEmail(this)) {
                null
            } else {
                "잘못된 이메일입니다."
            }
        }
        ValidateField.Password -> {
            if (ValidateUtils.isValidPassword(this)) {
                null
            } else {
                "잘못된 비밀번호입니다."
            }
        }
    }
}

enum class ValidateField {
    Email,
    Password
}