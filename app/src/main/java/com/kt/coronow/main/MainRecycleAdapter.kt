package com.kt.coronow.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kt.coronow.databinding.LayoutCityInfoBinding
import com.kt.coronow.databinding.LayoutDailyPatientBinding
import java.lang.RuntimeException

class MainRecycleAdapter(val list: List<ViewList>,val viewModel: MainViewModel):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewList.DAILY -> {
                val dailyBinding = LayoutDailyPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                DailyViewHoler(dailyBinding)
            }
            ViewList.CITY -> {
                val cityBinding = LayoutCityInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                CityViewHoler(cityBinding)
            }
            else -> {
                throw RuntimeException("Unknown ViewType!!")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(position) {
            ViewList.DAILY -> {
                (holder as DailyViewHoler).onBind()
            }
            ViewList.CITY -> {
                (holder as CityViewHoler).onBind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DailyViewHoler(val binding: LayoutDailyPatientBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            binding.apply {
                data = viewModel
            }
            binding.dailyChartCv.visibility = View.GONE


            binding.dailyUpdownBtn.setOnClickListener {
                when(binding.dailyChartCv.visibility) {
                    View.VISIBLE -> {
                        binding.dailyChartCv.visibility = View.GONE
                    }
                    else -> {
                        binding.dailyChartCv.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    inner class CityViewHoler(val binding: LayoutCityInfoBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind() {

        }
    }


}