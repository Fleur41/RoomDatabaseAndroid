package com.sam.roomdatabaseandroid.room

import android.content.Context
import androidx.room.Room
import com.sam.roomdatabaseandroid.room.AadhaarCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()
    }
    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao
    }
    @Provides
    @Singleton
    fun provideAadhaarCardDao(userDatabase: UserDatabase): AadhaarCardDao {
        return userDatabase.aadhaarCardDao
    }
}