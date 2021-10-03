package com.kt.coronow.repository

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object JsonRepository {
    const val BASE_URL = "http://ncov.mohw.go.kr/"
    const val SPAN_DATA = "<span class=\"data\">"
    const val SPAN_NUM = "<span class=\"num\">"
    const val SPAN_END = "</span>"
    const val SPAN_BEFORE = "<span class=\"before\">"

    var aboardCnt = "-"
    var countryCnt = ""
    

    fun getDailyInfo(dataList: List<MutableLiveData<String>>, jsonCalled: MutableLiveData<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            jsonCalled.postValue(false)
            val doc:Document? = Jsoup.connect(BASE_URL).get()
            val countryAndAboardCnt = doc?.select(".data")
            val totalCnt = doc?.select("div.liveNum ul li .num")
            val before = doc?.select(".before")


            if(!before.isNullOrEmpty() && !countryAndAboardCnt.isNullOrEmpty() && !totalCnt.isNullOrEmpty()){
                countryCnt = getInt(countryAndAboardCnt[0].toString(),true)
                aboardCnt = getInt(countryAndAboardCnt[1].toString(),false)

                val dailycnt = getInt(before[0].toString(),false)
                val total = getInt(totalCnt[0].toString(),false)

                val carecnt = getInt(before[1].toString(),false)
                val careTotalCnt = getInt(totalCnt[1].toString(),false)

                val careIngCnt = getInt(before[2].toString(),false)
                val careIngTotalCnt = getInt(totalCnt[2].toString(),false)

                val deathCnt = getInt(before[3].toString(),false)
                val deathTotalCnt = getInt(totalCnt[3].toString(),false)

                dataList[0].postValue(dailycnt)
                dataList[1].postValue(total)

                dataList[2].postValue(carecnt)
                dataList[3].postValue(careTotalCnt)

                dataList[4].postValue(careIngCnt)
                dataList[5].postValue(careIngTotalCnt)

                dataList[6].postValue(deathCnt)
                dataList[7].postValue(deathTotalCnt)


            }
        }

    }

    fun getInt(str:String,withoutDot:Boolean):String{
        var num = ""
        str.forEach {
            when(it) {
                in '0'..'9' -> {
                    num += it.toString()
                }
                ',' -> {
                    if(withoutDot) num += it.toString()
                }
            }
        }
        return num
    }

}