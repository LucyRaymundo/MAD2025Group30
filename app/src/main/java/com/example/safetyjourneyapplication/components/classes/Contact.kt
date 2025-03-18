package com.example.safetyjourneyapplication.components.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey var contactID: Int,
    val contactUserID: Int, // the id of user who's contact this is
    val contactUserName: String,
    val contactContactID: Int,  // foreign key = make sure its the user id of the contact
    val contactLabel: String,
    val emergencyAlertContact: Int, // whether they are the emergency contact that is alerted = 1 = true/ 0 = false
    val dateTimeCreated: LocalDateTime = LocalDateTime.now()
)
