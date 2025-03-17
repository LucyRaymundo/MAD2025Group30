package com.example.safetyjourneyapplication.components.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey var contactID: Int,
    val contactUserID: Int,
    val contactUserName: String,
    val contactContactID: Int,
    val contactLabel: String,
    val dateTimeCreated: LocalDateTime = LocalDateTime.now()
)
