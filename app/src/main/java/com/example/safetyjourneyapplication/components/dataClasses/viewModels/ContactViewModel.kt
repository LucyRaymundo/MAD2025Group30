package com.example.safetyjourneyapplication.components.dataClasses.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safetyjourneyapplication.components.dataClasses.Book

class ContactViewModel : ViewModel() {
    var contacts =
        mutableStateListOf( // mutable list and snapshotStateList = allows for modification and state recompositions
            Book(
                id = 1,
                title = "The Will of The Many",
                author = "James Islington",
                genre = "Fantasy",
                totalNumOfPages = 640
            ),
            Book(
                id = 2,
                title = "Red Rising",
                author = "Pierce Brown",
                genre = "Action & Adventure",
                totalNumOfPages = 525,
                currentPage = 126
            )
        )
}