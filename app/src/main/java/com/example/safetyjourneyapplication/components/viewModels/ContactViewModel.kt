package com.example.safetyjourneyapplication.components.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safetyjourneyapplication.components.dataClasses.Contact
import java.time.LocalDateTime

class ContactViewModel : ViewModel() {
    var contacts =
        mutableStateListOf( // mutable list and snapshotStateList = allows for modification and state recompositions
            Contact(
                contactID = 1,
                contactUserID = 1,
                contactUserName = "JennyKuang",
                contactContactID = 1, // need to edit
                contactLabel = "Mother",
                dateTimeCreated = LocalDateTime.now()
            )
        )
}