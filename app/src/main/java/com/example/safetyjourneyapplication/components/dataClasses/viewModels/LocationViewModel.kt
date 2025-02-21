package com.example.safetyjourneyapplication.components.dataClasses.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safetyjourneyapplication.components.dataClasses.Coordinate
import com.example.safetyjourneyapplication.components.dataClasses.Location

class LocationViewModel : ViewModel() {
    var locations =
        mutableStateListOf(
            Location(
                locationID = "001",
                locationName = "far,far away",
                locationAddress = "Where judas lost his boots",
                locationPostcode = "1BJ 0BS",
                locationCoordinate = Coordinate(latitude = 51.509865, longitude = -0.118092 )
            ) ) }

