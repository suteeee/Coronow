package com.kt.coronow.chart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.kt.coronow.repository.RestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChartViewModel:ViewModel() {
    var entries  = ArrayList<Entry>()
    val totalGet = MutableLiveData<Boolean>(false)

    fun entriesSet(list: ArrayList<String>) {
        entries.clear()
        Log.d("week",list.toString())

        for(i in 0 until list.size) {
            entries.add(Entry(i.toFloat(),list[i].toFloat()))
        }
    }

    fun switchChartToTotal() {
        if(totalGet.value == false) {
            CoroutineScope(Dispatchers.IO).launch {
                RestRepository.getAllDate(totalGet)
            }
        }
    }
}