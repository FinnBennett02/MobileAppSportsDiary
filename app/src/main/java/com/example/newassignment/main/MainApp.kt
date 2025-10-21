package com.example.newassignment.main

import android.app.Application
import com.example.newassignment.models.DiaryMemStore
import com.example.newassignment.models.DiaryModel
import com.example.newassignment.models.DiaryStore
import timber.log.Timber
import timber.log.Timber.Forest.i


class MainApp : Application() {

    val diaries = DiaryMemStore()
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Diary  started")


    }
}

