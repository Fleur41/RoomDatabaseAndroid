package com.sam.roomdatabaseandroid.room

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface AadhaarCardDao {
    @Upsert
    suspend fun upsert(aadhaarCard: AadhaarCard)
}