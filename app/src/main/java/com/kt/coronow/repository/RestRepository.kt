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
import kotlin.math.abs

object RestRepository {
    const val FIRST_DAY = "20200203"


    /*
    *
    * @param allIncdecList 누적 확진자 리스트
    * */
    val allIncdecList = ArrayList<String>() //누적 확진자 리스트
    val weekIncdecList = ArrayList<String>() //일주일 확진자 리스트
    val allDaysIncList = ArrayList<String>() //전체기간 확진자 리스트

    val allDaylist = ArrayList<String>()// 전체기간 리스트

    val localCntList = ArrayList<String>()
    val aboardCntList = ArrayList<String>()
    val clearCntList = ArrayList<String>()

    var dayIncMax = 0
    var alldayMax = 0 //전체기간 최다 확진자
    var allIncMax = 0 //누적 최다 확진자


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(CoronaApi.BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
        .build()

    fun getRestApi(apiCalled: MutableLiveData<Boolean>) {
        if(weekIncdecList.isEmpty()) {
            apiCalled.postValue(false)

            val week = DateRepository.getWeek()

            val CoronaService = retrofit.create(CoronaService::class.java)
            CoronaService
                .getKCorona(CoronaApi.KOREA_CORONA_API_KEY,"1","10", week[0],week[6])
                .enqueue(object : Callback<CoronaData> {
                    override fun onResponse(call: Call<CoronaData>, response: Response<CoronaData>) {
                        if(response.isSuccessful) {
                            val data = (response.body() as CoronaData)
                            data.body?.items?.item?.forEach {
                                if(it.gubun == "합계"){
                                    if(dayIncMax < it.incDec!!.toInt()) dayIncMax = it.incDec.toInt()
                                    weekIncdecList.add(it.incDec.toString())
                                    localCntList.add(it.localOccCnt.toString())
                                    aboardCntList.add(it.overFlowCnt.toString())
                                }
                            }
                            weekIncdecList.reverse()
                            localCntList.reverse()
                            aboardCntList.reverse()
                            apiCalled.postValue(true)
                        }
                    }
                    override fun onFailure(call: Call<CoronaData>, t: Throwable) {
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
                                val date = itemList[i].createDt?.substring(0..9)?.replace("-","")
                                allDaylist.add(date!!)

                                if(startday == 72328) { startday = 115925 } //수치 오차 보정(2021/4/21일)
                                if(date == "20210421"){ startday = 115194 }

                                allIncdecList.add(startday.toString())

                                if(i == itemSize) { allDaysIncList.add(startday.toString()) }

                                if(i < itemSize) {
                                    var endday = itemList[i+1].decideCnt?.toInt()
                                    if(endday == 72328) { endday = 115925 } //수치 오차 보정(2021/4/21일)

                                    val dcnt = abs(abs(startday!!) - abs(endday!!))

                                    if(alldayMax < dcnt) alldayMax = (dcnt)
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