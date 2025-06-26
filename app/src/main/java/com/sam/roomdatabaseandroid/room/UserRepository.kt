package com.sam.roomdatabaseandroid.room

import android.util.Log
import javax.inject.Inject
import kotlin.random.Random

class UserRepository @Inject constructor(
    private val userDatabase: UserDatabase
) {
    suspend fun insertUser(
    userName: String,
    email: String,
    fullName: String
    ) {
        val user = User(userName = userName, email = email, fullName = fullName)
        val userId = userDatabase.userDao.insertUser(user)
        Log.d("TAG", "User inserted with id: $userId")

    }
}