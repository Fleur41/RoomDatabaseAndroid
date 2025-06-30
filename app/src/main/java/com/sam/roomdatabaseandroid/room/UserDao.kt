package com.sam.roomdatabaseandroid.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM User")
    fun getAllUsersFlow(): Flow<List<User>>

    @Delete
    suspend fun deleteUser(user: User): Int

    @Query("DELETE FROM User WHERE id = :userId")
    suspend fun deleteAllUser(userId: Long): Int
}