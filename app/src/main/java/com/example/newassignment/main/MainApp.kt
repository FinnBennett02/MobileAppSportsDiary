package com.example.newassignment.main

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.newassignment.models.DiaryMemStore
import com.example.newassignment.models.DiaryStore
import timber.log.Timber
import timber.log.Timber.Forest.i

class MainApp : Application() {

    lateinit var diaries: DiaryStore

    override fun onCreate() {
        super.onCreate()


        // 1. Move the theme logic inside onCreate
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val mode = prefs.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setDefaultNightMode(mode)

        Timber.plant(Timber.DebugTree())
        diaries = DiaryMemStore(applicationContext)
        i("Diary App started")
    }
}
