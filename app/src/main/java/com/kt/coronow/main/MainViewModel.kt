package com.kt.coronow.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kt.coronow.repository.JsonRepository
import com.kt.coronow.repository.RestRepository
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {
    val isPageDown = MutableLiveData(false)
    val apiCalled = MutableLiveData<Boolean>(false)


    fun getApiData() {
        RestRepository.getRestApi(apiCalled)
    }
}