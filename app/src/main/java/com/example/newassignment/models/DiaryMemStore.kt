package com.example.newassignment.models

import timber.log.Timber.Forest.i

class DiaryMemStore : DiaryStore {
    val diaries = ArrayList<DiaryModel>()

    override fun findAll(): List<DiaryModel> {
        return diaries
    }

    override fun create(diary: DiaryModel) {
        diaries.add(diary)
    }

    fun logAll() {
        diaries.forEach { i("$it") }
    }
}