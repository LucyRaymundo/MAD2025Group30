package com.example.safetyjourneyapplication.components.dataClasses.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safetyjourneyapplication.components.dataClasses.Activity
import java.time.LocalDateTime

class ActivityViewModel : ViewModel() {
    var activities =
        mutableStateListOf( // mutable list and snapshotStateList = allows for modification and state recompositions
            Activity(
                activityID = 1,
                activityName = "First Journey",
                activityUserID = 1,
                activityUserName = "OliviaKuang",
                activityDescription = "aksjsnfjjgjgjgooof",
                activityStartLocation = ,
                activityStartTimeDate = LocalDateTime.now(),
                activityEndLocation = ,
                activityArriveTimeDate = LocalDateTime.now(),
                activityStatus =  ,
            )
        )
}