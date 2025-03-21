package com.example.safetyjourneyapplication.components.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.safetyjourneyapplication.components.classes.Activity
import com.example.safetyjourneyapplication.components.classes.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE contactUserID = :userId")
    suspend fun getContacts(userId: Int): List<Contact>

    @Query("SELECT contactUserName FROM contacts WHERE contactUserID = :userId AND emergencyAlertContact = 1")
    suspend fun getEmergencyContact(userId: Int): String?

    // add delete and edit queries

}