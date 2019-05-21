package com.example.fiveoclock.DATA

data class TimeResult(
    val error: Int?,
    val error_message: String?,
    val time: String?,
    val timezone: String?,
    val offset: Int?,
    val dayliight_savings: String?
)