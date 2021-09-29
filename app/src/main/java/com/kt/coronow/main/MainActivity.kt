package com.kt.coronow.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kt.coronow.R
import org.conscrypt.Conscrypt
import java.security.Security

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun fragmentReplace(layout:Int,fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(layout,fragment).commit()
    }
}