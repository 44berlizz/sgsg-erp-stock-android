package com.slogup.sgsgerpstock.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.slogup.sgsgerpstock.data.User

/**
 * Created by jessehj on 25/03/2019.
 */

@Database(entities = [(User::class)], version = 1)
@TypeConverters(SGTypeConverter::class)
abstract class SGDatabase : RoomDatabase() {
    abstract fun sgDao(): SGDao
}