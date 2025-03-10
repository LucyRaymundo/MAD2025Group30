package com.example.safetyjourneyapplication.components.classes

import java.time.LocalDateTime

data class Position(
    val positionID: Int,
    val positionActivityID: Int,
    val positionActivityName: String,
    val positionCoordinate: Coordinate,
    val positionDatetime: LocalDateTime = LocalDateTime.now()
)