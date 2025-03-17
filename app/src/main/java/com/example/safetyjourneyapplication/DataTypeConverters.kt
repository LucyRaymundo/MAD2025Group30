package com.example.safetyjourneyapplication

import androidx.room.TypeConverter
import com.example.safetyjourneyapplication.components.classes.Status
import java.time.LocalDateTime

class DataTypeConverters {

    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime): String {
        return localDateTime.toString()
    }

    @TypeConverter
    fun toLocalDateTime(localDateTime: String): LocalDateTime {
        return LocalDateTime.parse(localDateTime)
    }

    @TypeConverter
    fun fromStatus(status: Status): String {
        return status.id
    }

    @TypeConverter
    fun toStatus(status: String): Status {
        return Status.valueOf(status)
    }

}
