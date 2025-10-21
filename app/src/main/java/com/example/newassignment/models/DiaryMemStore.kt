package com.example.newassignment.models

import timber.log.Timber.Forest.i

var lastId =0L

internal fun getId(): Long {
    return lastId++
}



class DiaryMemStore : DiaryStore {
    val diaries = ArrayList<DiaryModel>()

    override fun findAll(): List<DiaryModel> {
        return diaries
    }

    override fun create(diary: DiaryModel) {
        diary.id = getId()
        diaries.add(diary)
        logAll()
    }
    override fun update(diary: DiaryModel) {
        var foundDiary: DiaryModel? = diaries.find { p -> p.id == diary.id }
        if (foundDiary != null) {
            foundDiary.title = diary.title
            foundDiary.description = diary.description
            foundDiary.goals = diary.goals
            foundDiary.assist = diary.assist
            foundDiary.rating = diary.rating
            logAll()
        }
    }

    override fun delete(id: String) {
        diaries.removeIf { p -> p.id.toString() == id }
        logAll()

    }

    fun logAll() {
        diaries.forEach { i("$it") }
    }
}