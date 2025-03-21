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
    suspend fun insertActivity(activity: Activity) // adds activity

    @Query("SELECT * FROM activities WHERE activityUserID = :userId AND activityStatusName = :completed OR activityStatusName = :abandoned")
    suspend fun getPastActivities(userId: Int, completed: String, abandoned: String): List<Activity> // returns activities that have been completed or abandoned i.e. past activities

    @Query("SELECT * FROM activities WHERE activityUserID = :userId AND activityStatusName = :started OR activityStatusName = :paused ORDER BY activityID DESC LIMIT 1")
    suspend fun getCurrentActivity(userId: Int, started: String, paused: String): Activity? // returns current journey

    @Query("SELECT * FROM activities WHERE activityUserID = :userId AND activityLeaveTimeDate > :currentTimeDate")
    suspend fun getFutureActivities(userId: Int, currentTimeDate: LocalDateTime): List<Activity> // returns future journeys = where leaveTimeDate is greater than the current time/date

    @Query("SELECT activityID FROM activities WHERE activityUserID = :userId ORDER BY activityID DESC LIMIT 1")
    suspend fun getActivityId(userId: Int): Int // returns activityId

    @Query("UPDATE activities SET activityStatusName = :updatedStatus WHERE activityID = :activityId" )
    suspend fun updateStatus(updatedStatus: String, activityId: Int) // updates activities status


}