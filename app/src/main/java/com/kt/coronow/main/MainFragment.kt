package com.kt.coronow.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kt.coronow.R
import com.kt.coronow.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    companion object { fun getInstance() = MainFragment() }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.main_fragment, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            data = viewModel
        }



        val list = listOf(
            ViewList(ViewList.DAILY),
            ViewList(ViewList.CITY)
        )

        val adapter = MainRecycleAdapter(list,viewModel)
        binding.mainRv.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.mainRv.adapter = adapter

        viewModel.initDailyInfo(binding.mainRv.adapter as MainRecycleAdapter)

        viewModel.dailyPatientCnt.observe(viewLifecycleOwner, {
            (binding.mainRv.adapter as MainRecycleAdapter).notifyDataSetChanged()
        })

        return binding.root
    }
}