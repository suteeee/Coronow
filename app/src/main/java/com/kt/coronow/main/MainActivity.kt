package com.kt.coronow.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kt.coronow.R
import com.kt.coronow.repository.DateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.conscrypt.Conscrypt
import java.security.Security

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DateRepository.getDate()
        DateRepository.getAllDate()

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getApiData()
        }

    }

    fun fragmentReplace(layout:Int,fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(layout,fragment).commit()
    }
}