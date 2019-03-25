package com.slogup.sgsgerpstock.data.repository

import androidx.lifecycle.LiveData
import com.slogup.sgsgerpstock.data.User

/**
 * Created by jessehj on 25/03/2019.
 */

interface SGRepository {
    fun saveUser(user: User)
    fun getAllUsers(): LiveData<List<User>>
    fun clearUser()
}