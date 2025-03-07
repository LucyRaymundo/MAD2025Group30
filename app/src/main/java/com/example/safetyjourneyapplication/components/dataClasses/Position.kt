package com.example.safetyjourneyapplication.components.dataClasses

// @Entity(tableName = "positions")
data class Position(
    // @PrimaryKey
    val positionID: String,
    val positionActivityID: String,
    val positionActivityName: String,
    val positionCoordinate: Coordinate,
    val positionDatetime: String
)