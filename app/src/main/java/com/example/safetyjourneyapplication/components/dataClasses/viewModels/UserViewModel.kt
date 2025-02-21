package com.example.safetyjourneyapplication.components.dataClasses.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safetyjourneyapplication.components.dataClasses.User

class UserViewModel : ViewModel() {
    var users =
        mutableStateListOf( // mutable list and snapshotStateList = allows for modification and state recompositions
            User(
                userID = 1,
                userFirstName = "Olivia",
                userLastName = "Kuang",
                userPhone = "07664910347",
                userName = "OliviaKuang23"

            )
        )
}