package com.example.safetyjourneyapplication.components.dataClasses

// @Entity(tableName = "users")
data class User (
    // @PrimaryKey
    var userID: Int,
    val userFirstName: String,
    val userLastName: String,
    val userPhone: String,
    val userName: String
)

