package com.example.safetyjourneyapplication.components.dataClasses

import java.time.LocalDateTime

data class Activity(
    var activityID: Int,
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