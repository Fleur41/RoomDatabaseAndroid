package com.sam.roomdatabaseandroid.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    entities = [User::class, AadhaarCard::class],
    exportSchema = true
)
//@AutoMigration(from = 1, to = 2)
@TypeConverters(
    value = [InstantTypeConverter::class]
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val aadhaarCardDao: AadhaarCardDao

//    if I was not to use ksp{arg("room.generateKotlin", "true")} from gradle, I woulduse this:
//    abstract fun getUserDao(): UserDao
//    abstract fun getAadhaarCardDao(): AadhaarCardDao

}