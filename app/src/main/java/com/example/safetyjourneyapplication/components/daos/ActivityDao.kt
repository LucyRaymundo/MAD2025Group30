package com.example.safetyjourneyapplication.components.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.safetyjourneyapplication.components.classes.Activity

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: Activity)

    @Query("SELECT * FROM activities WHERE activityUserId = :activityUserId")
    suspend fun getActivities(activityUserId: Int): List<Activity>

}