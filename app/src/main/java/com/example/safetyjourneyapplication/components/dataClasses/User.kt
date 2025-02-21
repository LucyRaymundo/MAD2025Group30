package com.example.safetyjourneyapplication.components.dataClasses

data class Book(
    var id: Int,
    val title: String,
    val author: String,
    val genre: String,
    val totalNumOfPages: Int,
    var currentPage: Int = 0
) {
    val readingProgress: Double
        get() = currentPage.toDouble() / totalNumOfPages * 100
}