package com.kt.coronow.chart

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.kt.coronow.R
import com.kt.coronow.repository.DateRepository
import com.kt.coronow.repository.JsonRepository
import com.kt.coronow.repository.RestRepository

class ChartMarkerView(context: Context, layoutResource: Int, val type: Int, vararg val circle:ImageView) : MarkerView(context, layoutResource) {
    var firstText:TextView = findViewById<TextView>(R.id.chartInfo_firstText)
    val secondText = findViewById<TextView>(R.id.chartInfo_secondText)
    val thirdText = findViewById<TextView>(R.id.chartInfo_thirdText)
    val fourthText = findViewById<TextView>(R.id.chartInfo_fourthText)

    override fun setChartView(chart: Chart<out ChartData<*>>?) {
        super.setChartView(chart)
    }

    override fun getOffsetForDrawingAtPoint(posX: Float, posY: Float): MPPointF {
        circle[0].x = posX - (circle[0].width / 2)
        circle[0].y = posY + (circle[0].height * 6.2f)

        return super.getOffsetForDrawingAtPoint(posX, posY)
    }

    override fun getOffset(): MPPointF {

        val x = (-(width /2)).toFloat()
        val y = (-1000).toFloat()

        return  MPPointF(x, y)
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {

        when(type) {
            chartFragment.SEVENDAYS -> {
                firstText.text = "합계 ${e?.y?.toInt().toString()} "
                secondText.text = "국내발생 ${RestRepository.localCntList[e?.x!!.toInt()]} "
                fourthText.text = "해외발생 ${RestRepository.aboardCntList[e?.x!!.toInt()]}"
            }
            chartFragment.ALLDAYS ->{
                val str1 = RestRepository.allDaylist[e?.x!!.toInt()].substring(4..5)
                val str2 = RestRepository.allDaylist[e?.x!!.toInt()].substring(6..7)

                val res = "$str1.$str2"

                firstText.text = "$res "
                secondText.text = "신규확진 ${e?.y?.toInt().toString()}"
            }
            chartFragment.TOTAL -> {
                val str1 = RestRepository.allDaylist[e?.x!!.toInt()].substring(4..5)
                val str2 = RestRepository.allDaylist[e?.x!!.toInt()].substring(6..7)

                val res = "$str1.$str2"

                firstText.text = "$res "
                thirdText.text = "누적확진 ${e?.y?.toInt().toString()}"
            }
        }

        //infoBinding.chartInfoSecondText.text = e?.y.toString()


        //Log.d( infoBinding.data?.secondText?.value ,"marker")

        super.refreshContent(e, highlight)
    }
}