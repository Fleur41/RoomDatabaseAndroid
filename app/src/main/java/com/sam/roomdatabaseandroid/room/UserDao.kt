package com.sam.roomdatabaseandroid.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>
}