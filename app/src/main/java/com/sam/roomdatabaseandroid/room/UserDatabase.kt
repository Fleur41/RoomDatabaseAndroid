package com.sam.roomdatabaseandroid.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    entities = [User::class, AadhaarCard::class],
    exportSchema = true
)
@TypeConverters(
    value = [InstantTypeConverter::class]
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val aadhaarCardDao: AadhaarCardDao

}