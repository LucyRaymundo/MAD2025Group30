package com.example.safetyjourneyapplication.components.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.safetyjourneyapplication.components.classes.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT userId FROM users WHERE userName = :userName AND userPassword = :userPassword")
    suspend fun checkUserExists(userName: String, userPassword: String): Int?

    @Query("SELECT userFirstName FROM users WHERE userId = :id")
    suspend fun getUserFirstName(id: Int): String

    @Query("SELECT userLastName FROM users WHERE userId = :id")
    suspend fun getUserLastName(id: Int): String



}