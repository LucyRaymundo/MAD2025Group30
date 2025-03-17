package com.example.safetyjourneyapplication.components.classes

import java.time.LocalDateTime

data class Position(
    val positionID: Int,
    val positionActivityID: Int,
    val positionActivityName: String,
    val positionLongitude: Int,
    val positionLatitude: Int,
    val positionDatetime: LocalDateTime = LocalDateTime.now()
)