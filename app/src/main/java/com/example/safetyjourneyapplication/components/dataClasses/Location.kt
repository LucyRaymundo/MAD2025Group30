package com.example.safetyjourneyapplication.components.dataClasses

data class Location(
    val locationID: String,
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