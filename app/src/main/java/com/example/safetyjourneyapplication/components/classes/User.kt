package com.example.safetyjourneyapplication.components.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val userFirstName: String,
    val userLastName: String,
    val userPhone: String,
    val userName: String,
    val userPassword: String,
    val userLatitude: Int = 0,
    val userLongitude: Int = 0,
    val userTimeStamp: LocalDateTime = LocalDateTime.now()
)

