package com.example.safetyjourneyapplication.components.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.safetyjourneyapplication.components.classes.Activity
import java.time.LocalDateTime

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: Activity)

    @Query("SELECT * FROM activities WHERE activityUserID = :userId")
    suspend fun getActivities(userId: Int): List<Activity>

    @Query("SELECT * FROM activities WHERE activityUserID = :userId ORDER BY activityID DESC LIMIT 1")
    suspend fun getMostRecentActivity(userId: Int): Activity?

    @Query("SELECT * FROM activities WHERE activityUserID = :userId AND activityLeaveTimeDate > :currentTimeDate")
    suspend fun getFutureActivities(userId: Int, currentTimeDate: LocalDateTime): List<Activity>


}