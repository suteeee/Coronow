package com.kt.coronow.repository

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object DateRepository {
    val calendar: Calendar = Calendar.getInstance()
    var today = ""

    var weekdays = ArrayList<String>()
    val weekDate = ArrayList<String>()

    val allDaylist = ArrayList<String>()

    val formatWithDot =  SimpleDateFormat("MM.dd", Locale.KOREA)

    fun getDate() :ArrayList<String>{
        val cur = System.currentTimeMillis()
        val date = Date(cur)

        //today
        val formatter = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(date)
        today = formatter

        Log.d("item", today)




        for(i in 6 downTo 0) {
            calendar.time = date
            calendar.add(Calendar.DATE,-i)
            val weekDateFormat =  SimpleDateFormat("yyyyMMdd", Locale.KOREA)
            weekdays.add(formatWithDot.format(calendar.time))
            weekDate.add(weekDateFormat.format((calendar.time)))

        }

        Log.d("week", weekdays.toString())

        return weekDate
    }

    fun getWeek() : ArrayList<String> {
        val list = ArrayList<String>()
        val cur = System.currentTimeMillis()
        val date = Date(cur)

        for(i in 6 downTo 0) {
            calendar.time = date
            calendar.add(Calendar.DATE,-i)
            val weekDateFormat =  SimpleDateFormat("yyyyMMdd", Locale.KOREA)
            list.add(weekDateFormat.format((calendar.time)))
        }
        return list
    }

    fun getAllDate(){
        val cur = System.currentTimeMillis()
        val date = Date(cur)
        calendar.set(2020,2,3)

        while(true) {
            val day = formatWithDot.format(calendar.time)
            if(day == weekdays[6]) break
            else{
                allDaylist.add(day)
                calendar.add(Calendar.DATE,+1)
            }
        }
    }
}