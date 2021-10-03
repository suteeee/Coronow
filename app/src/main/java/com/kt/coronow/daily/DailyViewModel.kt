package com.kt.coronow.daily

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kt.coronow.repository.JsonRepository

class DailyViewModel:ViewModel() {
    val dailyPatientCnt = MutableLiveData<String>("-")
    val totalPatientCnt = MutableLiveData<String>("-")

    val dailyCareCnt = MutableLiveData<String>("-")
    val dailyCareTotalCnt = MutableLiveData<String>("-")

    val careIngCnt = MutableLiveData<String>("-")
    val careIngTotalCnt = MutableLiveData<String>("-")

    val deathCnt = MutableLiveData<String>("-")
    val deathTotalCnt = MutableLiveData<String>("-")

    val dataList = listOf(dailyPatientCnt, totalPatientCnt,
        dailyCareCnt, dailyCareTotalCnt,
        careIngCnt, careIngTotalCnt,
        deathCnt,deathTotalCnt)

    fun initDailyInfo() {
        JsonRepository.getDailyInfo(dataList)
    }

}