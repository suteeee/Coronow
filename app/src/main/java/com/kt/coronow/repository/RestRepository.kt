package com.kt.coronow.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kt.coronow.restApi.AllData.CoronaAllData
import com.kt.coronow.restApi.data.CoronaData
import com.kt.coronow.restApi.service.CoronaAllService
import com.kt.coronow.restApi.service.CoronaApi
import com.kt.coronow.restApi.service.CoronaService
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import retrofit2.*

object RestRepository {
    const val FIRST_DAY = "20200203"

    val allIncdecList = ArrayList<String>() //누적 확진자 리스트
    val weekIncdecList = ArrayList<String>() //일주일 확진자 리스트
    val allDaysIncList = ArrayList<String>() //전체기간 확진자 리스트

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(CoronaApi.BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
        .build()

    fun getRestApi(apiCalled: MutableLiveData<Boolean>) {
        if(weekIncdecList.isEmpty()) {
            apiCalled.postValue(false)

            val week = DateRepository.getWeek()
            Log.d("weekdays",week.toString())


            val CoronaService = retrofit.create(CoronaService::class.java)
            CoronaService
                .getKCorona(CoronaApi.KOREA_CORONA_API_KEY,"1","10", week[0],week[6])
                .enqueue(object : Callback<CoronaData> {
                    override fun onResponse(call: Call<CoronaData>, response: Response<CoronaData>) {
                        if(response.isSuccessful) {
                            Log.d("item",response.message())
                            val data = (response.body() as CoronaData)
                            Log.d("item",data.toString())
                            data.body?.items?.item?.forEach {
                                if(it.gubun == "합계"){
                                    weekIncdecList.add(it.incDec.toString())
                                }
                            }
                            weekIncdecList.reverse()
                            Log.d("item", weekIncdecList.toString())
                            apiCalled.postValue(true)
                        }
                    }
                    override fun onFailure(call: Call<CoronaData>, t: Throwable) {
                        Log.d("item",t.message.toString())
                    }
                })
        }
    }

    fun getAllDate(get: MutableLiveData<Boolean>) {
        if(allDaysIncList.isEmpty() && allIncdecList.isEmpty()) {
            val AllService = retrofit.create(CoronaAllService::class.java)
            AllService.getAllCorona(CoronaApi.KOREA_CORONA_API_KEY,"1","10", FIRST_DAY,DateRepository.today)
                .enqueue(object:Callback<CoronaAllData> {
                    override fun onResponse(call: Call<CoronaAllData>, response: Response<CoronaAllData>
                    ) {
                        if(response.isSuccessful) {
                            val data = (response.body() as CoronaAllData)
                            val itemList = data.body?.items?.item
                            val itemSize = itemList?.size?.minus(1)

                            for(i in itemSize?.downTo(0)!!){
                                var startday = itemList[i].decideCnt?.toInt()
                                if(startday == 72328) { startday = 115925 } //수치 오차 보정(2021/4/21일)

                                allIncdecList.add(startday.toString())

                                if(i < itemSize) {
                                    var endday = itemList[i+1].decideCnt?.toInt()
                                    if(endday == 72328) { endday = 115925 } //수치 오차 보정(2021/4/21일)

                                    var dcnt = (startday?.minus(endday!!))
                                    if(dcnt!! < 0 ) dcnt = 0
                                    allDaysIncList.add(dcnt.toString())
                                }
                            }
                            get.value = true
                        }
                    }

                    override fun onFailure(call: Call<CoronaAllData>, t: Throwable) {

                    }
                })
        }
    }
}