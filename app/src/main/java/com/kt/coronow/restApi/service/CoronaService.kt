package com.kt.coronow.restApi.service

import com.kt.coronow.restApi.data.CoronaData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

object CoronaApi {
    val BASE_URL = "http://openapi.data.go.kr/openapi/service/rest/Covid19/"
    val KOREA_CORONA_API_KEY = "YgGRKGC6YuUfdJf6/dx92rusXLxof8/n3dmqHoDTnHImnzuSclh/GNdObZodM05y3NFb+UdMR704e3bfO2W11g=="
}

interface CoronaService {
    @GET("getCovid19SidoInfStateJson?")
    fun getKCorona(
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
    ): Call<CoronaData>

}