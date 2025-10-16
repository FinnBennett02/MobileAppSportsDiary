package com.example.newassignment.main

import android.app.Application
import com.example.newassignment.models.DiaryModel
import timber.log.Timber
import timber.log.Timber.Forest.i


class MainApp : Application() {

    val diaries = mutableListOf<DiaryModel>()
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Placemark started")


    }
}

