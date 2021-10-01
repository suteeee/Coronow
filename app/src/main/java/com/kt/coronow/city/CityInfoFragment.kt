package com.kt.coronow.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kt.coronow.R
import com.kt.coronow.databinding.LayoutCityInfoBinding

class CityInfoFragment:Fragment() {

    lateinit var binding: LayoutCityInfoBinding
    lateinit var viewModel: CityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_city_info,container, false)
        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            data = viewModel
        }


        return binding.root
    }
}