package com.sam.roomdatabaseandroid.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [User::class],
    exportSchema = true
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao

}