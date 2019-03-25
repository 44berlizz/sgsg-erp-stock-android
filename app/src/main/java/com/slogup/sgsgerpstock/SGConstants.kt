package com.slogup.sgsgerpstock

/**
 * Created by jessehj on 21/03/2019.
 */

object SGConstants {
    // #A
    // #B
    // #C
    object Common {
        const val rows = "rows"
        const val row = "row"
        const val count = "count"
        const val successCount = "successCount"
        const val failCount = "failCount"
        const val offset = "offset"
        const val limit = "limit"
        const val sort = "sort"
        const val startDate = "startDate"
        const val endDate = "endDate"
        const val id = "id"
        const val createdAt = "createdAt"
        const val updatedAt = "updatedAt"
        const val version = "version"
        const val status = "status"
    }

    // #D
    // #E
    object Error {
        const val domain = "domain"
        const val code = "code"
        const val param = "param"
        const val msg = "msg"
    }

    // #F
    // #G
    // #H
    // #I
    // #J
    // #K
    // #L
    // #M
    // #N
    object Network {
        const val x_auth_token = "x-auth-token"
        const val content_type = "Content-Type"
        const val application_json = "application/json"
    }
    // #O
    // #P
    // #Q
    // #R
    object RegEx {
        const val email_format =
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
    }

    // #S
    // #T
    // #U
    // #V
    object Value {
        const val limit_default = 25
        const val offset_default = 0
        const val connect_timeout_limit = 60L
        const val read_timeout_limit = 60L
    }
    // #W
    // #X
    // #Y
    // #Z


}