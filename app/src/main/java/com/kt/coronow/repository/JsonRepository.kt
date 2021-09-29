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

    fun getDailyInfo(dataList: List<MutableLiveData<String>>) {
        CoroutineScope(Dispatchers.IO).launch {
            val doc:Document? = Jsoup.connect(BASE_URL).get()
            val countryAndAboardCnt = doc?.select(".data")
            val totalCnt = doc?.select("div.liveNum ul li .num")
            Log.d("items",totalCnt.toString())
            val handler =android.os.Handler(Looper.getMainLooper())

            if(!countryAndAboardCnt.isNullOrEmpty()) {
                handler.post {
                    val countryCnt = removeTag(countryAndAboardCnt[0].toString(),"<span class=\"data\">","</span>")
                    val aboardCnt = removeTag(countryAndAboardCnt[1].toString(),"<span class=\"data\">","</span>")
                    var dailyCnt = (countryCnt.replace(",","").toInt() + aboardCnt.replace(",","").toInt()).toString()


                    dataList[0].value = (countryCnt)
                    dataList[2].value =(aboardCnt)
                    dataList[3].value = dailyCnt
                }
            }

            if(!totalCnt.isNullOrEmpty()){
                handler.post{
                    val careCnt = removeTag(totalCnt[1].toString(),"<span class=\"num\">","</span>")
                    val careIngCnt = removeTag(totalCnt[2].toString(),"<span class=\"num\">","</span>")
                    val deathCnt = removeTag(totalCnt[3].toString(),"<span class=\"num\">","</span>")
                    val total = totalCnt[0].toString().substring(48,totalCnt[0].toString().lastIndex - 6)
                    Log.d("items",total)

                    dataList[4].value = careCnt
                    dataList[5].value = careIngCnt
                    dataList[6].value = deathCnt
                    dataList[1].value = total
                }
            }

        }

    }

    fun removeTag(str:String,firstTag:String, endTag:String) :String{
        return str.replace(firstTag,"").replace(endTag,"").replace(",","")
    }

}