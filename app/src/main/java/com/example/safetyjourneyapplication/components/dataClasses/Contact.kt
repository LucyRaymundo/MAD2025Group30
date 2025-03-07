package com.example.safetyjourneyapplication.components.dataClasses

import java.time.LocalDate
import java.time.LocalDateTime

// @Entity(tableName = "contacts")
data class Contact(
    // @PrimaryKey
    var contactID: Int,
    val contactUserID: Int,
    val contactUserName: String,
    val contactContactID: Int,
    val contactLabel: String,
    val dateTimeCreated: LocalDateTime = LocalDateTime.now()
)