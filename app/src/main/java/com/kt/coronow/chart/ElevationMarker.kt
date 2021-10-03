package com.kt.coronow.chart

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

class ElevationMarker(context: Context, layoutResource: Int):MarkerView(context,layoutResource) {

    override fun getOffset(): MPPointF {
        val x = (-(width /2)).toFloat()
        val y = (-height).toFloat()

        return  MPPointF(x, y)
    }


}