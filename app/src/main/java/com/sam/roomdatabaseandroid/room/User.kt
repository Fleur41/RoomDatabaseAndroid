package com.sam.roomdatabaseandroid.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userName: String,
    val email: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,

)