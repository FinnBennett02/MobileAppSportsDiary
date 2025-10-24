package com.example.newassignment.main

import android.app.Application
import com.example.newassignment.models.DiaryMemStore

import com.example.newassignment.models.DiaryStore
import timber.log.Timber
import timber.log.Timber.Forest.i


class MainApp : Application() {

    lateinit var diaries: DiaryStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        // Initialize diaries here, where the context is fully available
        diaries = DiaryMemStore(applicationContext)

        i("Diary App started")
    }
}
