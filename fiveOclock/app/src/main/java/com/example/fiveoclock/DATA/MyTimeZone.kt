package com.example.fiveoclock.DATA

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "timeZones")
data class MyTimeZone (
    @PrimaryKey(autoGenerate = true) var timeZoneId : Long?,
    @ColumnInfo(name = "cityName") var cityName: String,
    @ColumnInfo(name = "friends") var friends: String,
    @ColumnInfo(name="timezone") var zone: String
) : Serializable
