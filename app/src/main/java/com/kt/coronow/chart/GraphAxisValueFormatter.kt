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
            return if(type != chartFragment.SEVENDAYS) {

                val str1 = list[idx].substring(4..5)
                val str2 = list[idx].substring(6..7)

                val res = "$str1.$str2"
                idx++

                res
            }else {
             return   list[idx++]
            }

        }
        catch (e:IndexOutOfBoundsException) {
        }
        return list[0]
    }

}