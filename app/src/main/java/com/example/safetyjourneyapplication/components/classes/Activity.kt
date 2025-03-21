package com.example.safetyjourneyapplication.components.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "activities")
data class Activity(
    @PrimaryKey var activityID: Int = 0,
    val activityName: String = "",
    val activityUserID: Int,
    val activityDescription: String = "",
    val activityStartLocationID: Int = 0,
    val activityStartLocationName: String,
    val activityLeaveTimeDate: LocalDateTime = LocalDateTime.now(),
    val activityDestinationID: Int = 0,
    val activityDestinationName: String,
    val activityArriveTimeDate: LocalDateTime,
    val activityStatusID: Int = 0,
    val activityStatusName: String
)
