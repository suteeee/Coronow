package com.kt.coronow.chart

import android.util.Log
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.kt.coronow.main.MainViewModel

class GraphAxisValueFormatter(val list:ArrayList<String>, val type:Int):ValueFormatter(){
    var idx = 0

    override fun getFormattedValue(value: Float): String {
        try {
            idx = value.toInt()
            return list[idx++]
        }
        catch (e:IndexOutOfBoundsException) {
        }
        return list[0]
    }

}