package com.slogup.sgsgerpstock.data.repository

import androidx.room.TypeConverter
import com.slogup.sgsgerpstock.data.User
import java.util.*
import java.util.jar.Attributes

/**
 * Created by jessehj on 25/03/2019.
 */

class SGTypeConverter {
    @TypeConverter
    fun fromUserName(name: User.Name?): String? {
        if (name != null) {
            return String.format(Locale.KOREA, "%s,%s", name.firstName, name.lastName)
        }
        return null
    }

    @TypeConverter
    fun toUserName(value: String?): User.Name? {
        if (value != null) {
            val pieces = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return User.Name(pieces[0], pieces[1])
        }
        return null
    }
}