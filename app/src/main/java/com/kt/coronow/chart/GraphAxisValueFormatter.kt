package com.kt.coronow.chart

import android.util.Log
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.kt.coronow.main.MainViewModel

class GraphAxisValueFormatter(val list:ArrayList<String>):ValueFormatter(){
    var idx = 0

    override fun getFormattedValue(value: Float): String {
        idx = value.toInt()
        Log.d("weekidx",idx.toString())
        return list[idx]
    }

}