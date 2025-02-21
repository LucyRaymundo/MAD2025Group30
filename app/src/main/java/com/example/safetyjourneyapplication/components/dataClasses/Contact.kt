package com.example.safetyjourneyapplication.components.dataClasses

import java.time.LocalDate
import java.time.LocalDateTime

data class Contact(
    var contactID: Int,
    val contactUserID: Int,
    val contactUserName: String,
    val contactContactID: Int,
    val contactLabel: String,
    val dateTimeCreated: LocalDateTime = LocalDateTime.now(),
)