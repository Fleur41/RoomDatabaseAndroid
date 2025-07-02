package com.sam.roomdatabaseandroid.room

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.coroutines.flow.Flow

//Intermediate rep of user and aadhaar card
data class UserAndAadhaarCard(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val aadhaarCard: AadhaarCard?
//    when using a flow from userdao (fun getAllUsersWithAadhaarCard(): Flow<List<UserAndAadhaarCard>>)
//u need to keep the aadhaarCard as null(val aadhaarCard: AadhaarCard?)
)
