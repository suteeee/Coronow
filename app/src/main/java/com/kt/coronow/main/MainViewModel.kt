package com.kt.coronow.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kt.coronow.repository.JsonRepository

class MainViewModel : ViewModel() {
    val countryPatientCnt = MutableLiveData<String>()
    val totalPatientCnt = MutableLiveData<String>()
    val aboardPatientCnt = MutableLiveData<String>()
    val dailyPatientCnt = MutableLiveData<String>()

    val carePatientCnt = MutableLiveData<String>()
    val careIngPatientCnt = MutableLiveData<String>()
    val deathPatientCnt = MutableLiveData<String>()

    val dataList = listOf(countryPatientCnt, totalPatientCnt, aboardPatientCnt, dailyPatientCnt, carePatientCnt, careIngPatientCnt, deathPatientCnt)




    fun initDailyInfo(adapter: MainRecycleAdapter) {
        JsonRepository.getDailyInfo(dataList)
    }

}