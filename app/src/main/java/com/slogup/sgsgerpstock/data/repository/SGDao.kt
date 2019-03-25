package com.slogup.sgsgerpstock.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.slogup.sgsgerpstock.data.User

/**
 * Created by jessehj on 25/03/2019.
 */

@Dao
interface SGDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    fun clearUser(vararg user: User?)

    @Query("SELECT * FROM user_table ORDER BY aId ASC")
    fun getAllUsers(): LiveData<List<User>>
}