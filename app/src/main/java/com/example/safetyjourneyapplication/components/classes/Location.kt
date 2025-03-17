package com.example.safetyjourneyapplication.components.classes

data class Location(
    val locationID: Int,
    val locationName: String,
    val locationAddress: String,
    val locationPostcode: String,
    val locationCoordinate: Int,
    val locationLongitude: Int,
)