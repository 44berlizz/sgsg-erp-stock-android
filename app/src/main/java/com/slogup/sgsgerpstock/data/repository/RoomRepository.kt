package com.slogup.sgsgerpstock.data.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.slogup.sgsgerpstock.AppManager
import com.slogup.sgsgerpstock.SGApplication
import com.slogup.sgsgerpstock.data.User

/**
 * Created by jessehj on 25/03/2019.
 */

class RoomRepository : SGRepository {
    private val sgDao: SGDao = SGApplication.instance.database.sgDao()
    private val allUsers: LiveData<List<User>>

    init {
        allUsers = sgDao.getAllUsers()
    }

    override fun saveUser(user: User) {
        InsertAsyncTask(sgDao).execute(user)
    }

    override fun getAllUsers(): LiveData<List<User>> = allUsers

    override fun clearUser() {
        allUsers.value?.toTypedArray()?.let {
            DeleteAsyncTask(sgDao).execute(*it)
        }
    }

    private class InsertAsyncTask internal constructor(
        private val dao: SGDao
    ) : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg params: User): Void? {
            dao.insertUser(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(
        private val dao: SGDao
    ) : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg params: User): Void? {
            dao.clearUser(*params)
            return null
        }
    }
}