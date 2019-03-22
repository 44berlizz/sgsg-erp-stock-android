package com.slogup.sgsgerpstock.network

import com.google.gson.annotations.SerializedName
import com.slogup.sgsgerpstock.SGConstants

/**
 * Created by jessehj on 21/03/2019.
 */

data class RowData<T>(
    @SerializedName(SGConstants.Common.row)
    val row: T,
    @SerializedName(SGConstants.Common.successCount)
    val successCount: Int?,
    @SerializedName(SGConstants.Common.failCount)
    val failCount: Int?
)

data class RowsData<T>(
    @SerializedName(SGConstants.Common.rows)
    val rows: MutableList<T>,
    @SerializedName(SGConstants.Common.count)
    val count: Int?
)