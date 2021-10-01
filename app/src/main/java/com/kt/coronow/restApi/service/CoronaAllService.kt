package com.kt.coronow.restApi.service

import com.kt.coronow.restApi.AllData.CoronaAllData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoronaAllService {
    @GET("getCovid19InfStateJson?")
    fun getAllCorona(
        @Query("serviceKey")
        key:String,
        @Query("pageNo")
        page:String,
        @Query("numOfRows")
        numOfRows:String,
        @Query("startCreateDt")
        startCreateDt:String,
        @Query("endCreateDt")
        end:String
    ): Call<CoronaAllData>

}