package com.sam.roomdatabaseandroid.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant


class InstantTypeConverter {
    @TypeConverter
    fun fromInstant(data: Instant): String {
        return data.toString()
    }
    @TypeConverter
    fun toInstant(data: String): Instant {
        return Instant.parse(data)
    }

//    @TypeConverter
//    fun fromInstant(data: Instant?): String? {
//        return data?.toString()
//    }
//
//    @TypeConverter
//    fun toInstant(data: String?): Instant? {
//        return data?.let { Instant.parse(it) }
//    }
}