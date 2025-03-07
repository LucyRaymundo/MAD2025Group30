package com.example.safetyjourneyapplication.components.dataClasses

// @Entity(tableName = "locations")
data class Location(
    // @PrimaryKey
    val locationID: Int,
    val locationName: String,
    val locationAddress: String,
    val locationPostcode: String,
    val locationCoordinate: Coordinate
)

data class Coordinate(
    val latitude: Double,
    val longitude: Double
) {
}