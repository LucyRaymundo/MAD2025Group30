package com.example.safetyjourneyapplication.components.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safetyjourneyapplication.components.classes.Activity
import com.example.safetyjourneyapplication.components.classes.Status
import java.time.LocalDateTime

class ActivityViewModel(private val LocationViewModel: LocationViewModel) : ViewModel() {
    /* var activities =
        mutableStateListOf( // mutable list and snapshotStateList = allows for modification and state recompositions
            Activity(
                activityID = 1,
                activityName = "First Journey",
                activityUserID = 1,
                activityUserName = "OliviaKuang",
                activityDescription = "aksjsnfjjgjgjgooof",
                activityStartLocation = LocationViewModel.locations.find { it.locationID == 1 }!!,
                activityStartTimeDate = LocalDateTime.now(),
                activityEndLocation = LocationViewModel.locations.find { it.locationID == 2 }!!,
                activityArriveTimeDate = LocalDateTime.now(),
                activityStatus = Status.PENDING
            )
        ) */
}