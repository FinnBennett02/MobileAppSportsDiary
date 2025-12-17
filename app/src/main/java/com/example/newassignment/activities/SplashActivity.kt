package com.example.newassignment.activities

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install the splash screen BEFORE super.onCreate()
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // No setContentView() here — remove it completely

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, DiaryListActivity::class.java))
            finish()
        }, 1200)
    }
}

