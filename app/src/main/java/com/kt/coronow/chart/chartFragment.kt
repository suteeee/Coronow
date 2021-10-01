package com.kt.coronow.chart

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.kt.coronow.R
import com.kt.coronow.databinding.ChartFragmentBinding
import com.kt.coronow.main.MainActivity
import com.kt.coronow.main.MainViewModel
import com.kt.coronow.repository.DateRepository
import com.kt.coronow.repository.RestRepository
import java.util.*
import kotlin.collections.ArrayList


class chartFragment : Fragment() {

    private lateinit var viewModel: ChartViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding:ChartFragmentBinding
    private val chart:LineChart by lazy {binding.chart}
    val SEVENDAYS = 0
    val ALLDAYS = 1
    val TOTAL = 2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)
        mainViewModel = (requireActivity() as MainActivity).viewModel

        binding = DataBindingUtil.inflate(inflater, R.layout.chart_fragment, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            data = viewModel
        }

        mainViewModel.apiCalled.observe(viewLifecycleOwner, {
            if(it) {
                Log.d("week",DateRepository.weekdays.toString())
                viewModel.entriesSet(RestRepository.weekIncdecList)
                chartDraw(DateRepository.weekdays,SEVENDAYS)
            }
        })

        binding.chartTotalBtn.setOnClickListener {
            viewModel.switchChartToTotal()
        }



        viewModel.totalGet.observe(viewLifecycleOwner,{
            if(it) {
                Log.d("total",it.toString())
                viewModel.entriesSet(RestRepository.allIncdecList)
                chartDraw(RestRepository.allIncdecList,TOTAL)

            }
        })

        return binding.root
    }

    fun chartDraw(values: ArrayList<String>,type:Int) {
        val lineDataSet = LineDataSet(viewModel.entries, "확진환자")
        lineDataSet.run {
            when(type) {
                SEVENDAYS -> {
                    lineWidth = 2F
                    circleRadius = 6F
                    setDrawCircleHole(true)
                    setDrawCircles(true)
                    setDrawValues(true)
                }
                else -> {
                    lineWidth = 2F
                    setDrawCircleHole(false)
                    setDrawCircles(false)
                    setDrawValues(false)
                }
            }

            setCircleColor(Color.parseColor("#FFA1B4DC"))
            circleHoleColor = Color.BLUE
            color = Color.parseColor("#FFA1B4DC")

            setDrawHorizontalHighlightIndicator(false)
            setDrawHighlightIndicators(false)

            valueTextColor  = Color.BLACK
            valueTextSize = 10f
        }


        val xAxis = chart.xAxis
        xAxis.run {
            if(type != 0) {
                setDrawLabels(false)
            }
            textColor = Color.BLACK
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = GraphAxisValueFormatter(values)
            labelCount = values.size-1
            enableGridDashedLine(8f,24f,0f)
        }


        val yLAxis: YAxis = chart.axisLeft
        yLAxis.run {
            textColor = Color.BLACK

        }

        val yRAxis: YAxis = chart.axisRight
        yRAxis.run {
            setDrawLabels(false)
            setDrawAxisLine(false)
            setDrawGridLines(false)
        }

        val description = Description()
        description.text = ""

        chart.apply {
            isDoubleTapToZoomEnabled = false
            setDrawGridBackground(false)
            setDescription(description)
            setPinchZoom(false)
            //animateY(1000)
            invalidate()
        }

        val linedata = LineData(lineDataSet)
        chart.data = linedata

    }



}