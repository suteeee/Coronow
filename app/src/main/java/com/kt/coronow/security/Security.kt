package com.kt.coronow.security

import android.app.Application
import org.conscrypt.Conscrypt
import java.security.Security

class Security:Application() {
    override fun onCreate() {
        super.onCreate()
        Security.insertProviderAt(Conscrypt.newProvider(), 1)
    }
}