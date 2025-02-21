package com.example.safetyjourneyapplication.components.dataClasses.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safetyjourneyapplication.components.dataClasses.Position
import com.example.safetyjourneyapplication.components.dataClasses.Coordinate


class PositionViewModel : ViewModel() {
    var positions =
        mutableStateListOf(
            Position(
                positionID = "pos123",
                positionActivityID = "act789",
                positionActivityName = "Morning Jog",
                positionCoordinate = Coordinate(latitude = 51.509865, longitude = -0.118092),
                positionDatetime = "2024-09-27T06:30:00.000Z"
            ) ) }