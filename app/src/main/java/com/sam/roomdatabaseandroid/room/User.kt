package com.sam.roomdatabaseandroid.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import kotlin.time.ExperimentalTime


//@OptIn(ExperimentalTime::class) constructor
@Entity
data class User @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userName: String,
    val email: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @Embedded(prefix = "address_")
    val address: Address,

   val createdAt: Instant //Convert A-> B and B->A using type converter
//    val createdAt: Instant? = Instant.now() //Convert A-> B and B->A using type converter


)

data class Address(
    val city: String,
//    val building: String
)

// Preffix  ("address_") will be applied for all things within data class Address e.g
//val address_city: String,
//val address_building: String within the data class User