package com.sam.roomdatabaseandroid.room

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDatabase: UserDatabase
) {
    suspend fun insertUser(
    userName: String,
    email: String,
    fullName: String,
    address: Address
    ): Long {
        val user = User(userName = userName, email = email, fullName = fullName, address = address, createdAt = Instant.now())
        val userId = userDatabase.userDao.insertUser(user)
        Log.d("TAG", "User inserted with id: $userId")
        return userId
    }

    suspend fun insertUser(user : User) {
//        val user = User(userName = userName, email = email, fullName = fullName)
        val userId = userDatabase.userDao.insertUser(user)
        Log.d("TAG", "User inserted with id: $userId")

    }

    suspend fun getAllUsers(): List<User> {
        return userDatabase.userDao.getAllUsers()
    }

    fun getAllUsersFlow(): Flow<List<User>> {
        return userDatabase.userDao.getAllUsersFlow()
    }

    suspend fun deleteUser(user: User): Int {
        return userDatabase.userDao.deleteUser(user)

    }

    suspend fun insertAadhaarCard(aadhaarCard: AadhaarCard) {
        userDatabase.aadhaarCardDao.upsert(aadhaarCard)
    }

     fun getAllUsersWithAadhaarCard(): Flow<List<UserAndAadhaarCard>> {
        return userDatabase.userDao.getAllUsersWithAadhaarCard()
    }
}

