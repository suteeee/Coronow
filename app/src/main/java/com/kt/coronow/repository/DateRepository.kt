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
    val formatFullDay =  SimpleDateFormat("yyyyMMdd", Locale.KOREA)

    fun getDate() :ArrayList<String>{
        val cur = System.currentTimeMillis()
        val date = Date(cur)

        //today
        val formatter = formatFullDay.format(date)
        today = formatter

        for(i in 6 downTo 0) {
            calendar.time = date
            calendar.add(Calendar.DATE,-i)
            val weekDateFormat =  SimpleDateFormat("yyyyMMdd", Locale.KOREA)
            weekdays.add(formatWithDot.format(calendar.time))
            weekDate.add(weekDateFormat.format((calendar.time)))

        }


        return weekDate
    }

    fun getWeek() : ArrayList<String> {
        val list = ArrayList<String>()
        val cur = System.currentTimeMillis()
        val date = Date(cur)

        for(i in 6 downTo 0) {
            calendar.time = date
            calendar.add(Calendar.DATE,-i)
            list.add(formatFullDay.format((calendar.time)))
        }
        return list
    }

    fun getAllDate(){
        calendar.set(2020,1,3)

        while(true) {
            val day = formatFullDay.format(calendar.time)



            allDaylist.add(day)


            calendar.add(Calendar.DATE,+1)
            if(day == today) break

        }
        Log.d("day",allDaylist.last())
        Log.d("day",allDaylist.size.toString())
    }
}