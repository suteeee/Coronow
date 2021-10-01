package com.kt.coronow.daily

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kt.coronow.R
import com.kt.coronow.databinding.LayoutDailyPatientBinding
import com.kt.coronow.main.MainActivity
import com.kt.coronow.main.MainViewModel
import com.kt.coronow.repository.RestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DailyPatientFragment:Fragment() {
    lateinit var binding: LayoutDailyPatientBinding
    lateinit var viewModel: DailyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.layout_daily_patient,container, false)
        viewModel = ViewModelProvider(this).get(DailyViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            data = viewModel
        }

        viewModel.initDailyInfo()

        return binding.root
    }

}