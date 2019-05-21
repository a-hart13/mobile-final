package com.example.fiveoclock.DATA

import android.arch.persistence.room.*

@Dao
interface timeZoneDAO {
    @Query("SELECT * FROM timeZones")
    fun getAllTimeZones(): List <MyTimeZone>

    @Insert
    fun insertTimeZone(timeZone: MyTimeZone):Long

    @Delete
    fun deleteTimeZone(timeZone: MyTimeZone)

    @Update
    fun updateTimeZone(timeZone: MyTimeZone)
}