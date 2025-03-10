package com.example.safetyjourneyapplication.components.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "activities")
data class Activity(
    @PrimaryKey var activityID: Int,
    val activityName: String,
    val activityUserID: Int,
    val activityUserName: String,
    val activityDescription: String,
    val activityStartLocation: Location,
    val activityStartTimeDate: LocalDateTime = LocalDateTime.now(),
    val activityEndLocation: Location,
    val activityArriveTimeDate: LocalDateTime = LocalDateTime.now(),
    val activityStatus: Status

)