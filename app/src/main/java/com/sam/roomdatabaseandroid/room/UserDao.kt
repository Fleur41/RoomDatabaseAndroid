package com.sam.roomdatabaseandroid.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long
}