package com.sam.roomdatabaseandroid.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AadhaarCard (
    @PrimaryKey
    val id: String,
    val userId: Long,

)