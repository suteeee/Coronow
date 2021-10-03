package com.kt.coronow.chart

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
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
import com.kt.coronow.databinding.LayoutChartInfoBinding
import com.kt.coronow.main.MainActivity
import com.kt.coronow.main.MainViewModel
import com.kt.coronow.repository.DateRepository
import com.kt.coronow.repository.JsonRepository
import com.kt.coronow.repository.RestRepository
import java.util.*
import kotlin.collections.ArrayList


class chartFragment : Fragment() {

    private lateinit var viewModel: ChartViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding:ChartFragmentBinding
    private lateinit var infoBinding:LayoutChartInfoBinding


    private val chart:LineChart by lazy {binding.chart}
    private val sevenBtn by lazy {binding.chartSevenDaysBtn}
    private val alldayBtn by lazy {binding.chartAllDaysBtn}
    private val totalBtn by lazy {binding.chartTotalBtn}

    companion object{
        val SEVENDAYS = 0
        val ALLDAYS = 1
        val TOTAL = 2
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)
        mainViewModel = (requireActivity() as MainActivity).viewModel
        infoBinding =  LayoutChartInfoBinding.inflate(LayoutInflater.from(context),container,false)
        infoBinding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.chart_fragment, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            data = viewModel
        }

        mainViewModel.apiCalled.observe(viewLifecycleOwner, {
            if(it) {
                viewModel.entriesSet(RestRepository.weekIncdecList)
                chartDraw(DateRepository.weekdays,SEVENDAYS)
            }
        })

        sevenBtn.setOnClickListener {
            viewModel.entriesSet(RestRepository.weekIncdecList)
            chartDraw(DateRepository.weekdays,SEVENDAYS)
            setSelectButtonDesign(it as Button)
            setUnSelectButtonDesign(alldayBtn,totalBtn)

        }

        totalBtn.setOnClickListener {
            viewModel.switchChartToTotal()

            setSelectButtonDesign(it as Button)
            setUnSelectButtonDesign(sevenBtn,alldayBtn)
        }

        alldayBtn.setOnClickListener {
            viewModel.switchChartToAllDays()

            setSelectButtonDesign(it as Button)
            setUnSelectButtonDesign(sevenBtn,totalBtn)
        }



        viewModel.totalGet.observe(viewLifecycleOwner,{
            if(it) {
                viewModel.entriesSet(RestRepository.allIncdecList)
                chartDraw(RestRepository.allDaylist,TOTAL)

            }
        })

        viewModel.alldayGet.observe(viewLifecycleOwner,{
            if(it) {
                viewModel.entriesSet(RestRepository.allDaysIncList)
                chartDraw(RestRepository.allDaylist,ALLDAYS)
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
                    color = Color.parseColor("#FFA1B4DC")
                }
                else -> {
                    lineWidth = 1F
                    setDrawCircleHole(false)
                    setDrawCircles(false)
                    setDrawValues(false)
                    color = ResourcesCompat.getColor(resources,R.color.highlight_text_color,null)
                }

            }

            setCircleColor(Color.parseColor("#FFA1B4DC"))
            circleHoleColor = Color.BLUE


            setDrawHorizontalHighlightIndicator(false)
            setDrawVerticalHighlightIndicator(true)
            //setDrawHighlightIndicators(true)
            highlightLineWidth = 2f

            valueTextColor  = Color.BLACK
            valueTextSize = 10f
        }


        val xAxis = chart.xAxis
        xAxis.run {
           if(type != SEVENDAYS) {
                setDrawLabels(true)

            } else {
                setDrawLabels(true)
            }
            textColor = Color.BLACK
            position = XAxis.XAxisPosition.BOTTOM
            labelCount = 6
            valueFormatter = GraphAxisValueFormatter(values,type)
            setDrawGridLines(false)
            enableGridDashedLine(8f,24f,0f)
        }


        val yLAxis: YAxis = chart.axisLeft
        yLAxis.run {
            textColor = Color.BLACK
            var ymax = 0f
            when(type){
                SEVENDAYS -> {
                    ymax = calY(RestRepository.dayIncMax).toFloat()
                }
                ALLDAYS -> {
                    ymax = calY(RestRepository.alldayMax).toFloat()
                }
                TOTAL -> {
                    ymax = calY(RestRepository.allIncdecList.last().toInt()).toFloat()
                }

            }
            Log.d("max","$ymax")
            labelCount = 6
            axisMaximum = ymax

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

            val mark = ChartMarkerView(requireContext(),layoutResource = R.layout.layout_chart_info,type,width)
            mark.chartView = this
            marker = mark

            invalidate()
        }

        val linedata = LineData(lineDataSet)
        chart.data = linedata

    }

    fun setSelectButtonDesign(b:Button) {
        b.background = ResourcesCompat.getDrawable(resources,R.drawable.button_select,null)
        b.setTextColor(ResourcesCompat.getColor(resources,R.color.blue,null))
    }

    fun setUnSelectButtonDesign(vararg b:Button) {

        b.forEach {
            it.background = ResourcesCompat.getDrawable(resources,R.drawable.button_unselect,null)
            it.setTextColor(ResourcesCompat.getColor(resources,R.color.main_text_color,null))
        }

    }

    fun calY(y:Int):Int {
        val n = y.toString()
        var res = ""
        for(i in n.indices){
            res += if(i == 0) {
                (n[i]+1).toString()
            } else{
                "0"
            }
        }

        return res.toInt()
    }

}