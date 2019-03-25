package com.slogup.sgsgerpstock.util

import com.slogup.sgsgerpstock.SGConstants
import java.util.regex.Pattern

/**
 * Created by jessehj on 22/03/2019.
 */

object ValidateUtils {
    fun isValidEmail(email: String) : Boolean {
        val pattern = Pattern.compile(SGConstants.RegEx.email_format)
        return pattern.matcher(email).matches()
    }

    fun isValidPassword(password: String) : Boolean {
        return password.length >= 8
    }
}

enum class ValidateField {
    Email,
    Password
}
