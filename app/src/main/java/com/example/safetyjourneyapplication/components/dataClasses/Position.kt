package com.example.safetyjourneyapplication.components.dataClasses

data class Position(
    val positionID: String,
    val positionActivityID: String,
    val positionActivityName: String,
    val positionCoordinate: Coordinate,
    val positionDatetime: String
)