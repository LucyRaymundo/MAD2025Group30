package com.example.safetyjourneyapplication

import androidx.room.TypeConverter
import com.example.safetyjourneyapplication.components.classes.Coordinate
import com.example.safetyjourneyapplication.components.classes.Location
import com.example.safetyjourneyapplication.components.classes.Status
import java.time.LocalDateTime
import com.google.gson.Gson

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
    fun fromLocation(location: Location): String {
        return Gson().toJson(location) // location converted from type location into string
    }

    @TypeConverter
    fun toLocation(location: String): Location {
        return Gson().fromJson(location, Location::class.java) // location converted from type string into location
    }

    @TypeConverter
    fun fromCoordinate(coordinate: Coordinate): String {
        return Gson().toJson(coordinate) // coordinate converted from type coordinate into string
    }

    @TypeConverter
    fun toCoordinate(coordinate: String): Coordinate {
        return Gson().fromJson(coordinate, Coordinate::class.java) // coordinate converted from type string into coordinate
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
