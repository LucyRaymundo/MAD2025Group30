package com.example.safetyjourneyapplication.components.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "activities")
data class Activity(
    @PrimaryKey var activityID: Int = 0,
    val activityName: String,
    val activityUserID: Int,
    val activityUserUsername: String,
    val activityDescription: String,
    val activityStartLocationID: Int,
    val activityStartLocationName: String,
    val activityLeaveTimeDate: LocalDateTime = LocalDateTime.now(),
    val activityDestinationID: Int,
    val activityDestinationName: String,
    val activityArriveTimeDate: LocalDateTime = LocalDateTime.now(),
    val activityStatusID: Int,
    val activityStatusName: String
)
