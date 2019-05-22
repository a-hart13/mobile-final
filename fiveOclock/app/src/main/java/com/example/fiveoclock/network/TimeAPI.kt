package com.example.fiveoclock.network

import com.example.fiveoclock.DATA.TimeResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface TimeAPI {

    @GET("/data/2.5/weather") //what

    fun getWeather(@Query("q") base: String,

                   @Query("units") units: String,

                   @Query("appid") appid: String

    ) : Call<TimeResult>

}

