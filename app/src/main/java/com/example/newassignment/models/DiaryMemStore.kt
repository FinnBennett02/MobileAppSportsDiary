package com.example.newassignment.models

import android.content.Context
import com.example.newassignment.helpers.JSONSerializer
import timber.log.Timber.Forest.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class DiaryMemStore(private val context: Context) : DiaryStore {

    var diaries = ArrayList<DiaryModel>()


    private val serializer: JSONSerializer = JSONSerializer(context)

    init {

        if (serializer.deserialize().isNotEmpty()) {
            diaries = serializer.deserialize() as ArrayList<DiaryModel>
            val maxId = diaries.maxOfOrNull { it.id } ?: -1
            lastId = maxId + 1
        }
        logAll()
    }

    override fun findAll(): List<DiaryModel> {
        return diaries
    }

    override fun create(diary: DiaryModel) {
        diary.id = getId()
        diaries.add(diary)
        serialize() // Save changes
        logAll()
    }

    override fun update(diary: DiaryModel) {
        val foundDiary: DiaryModel? = diaries.find { p -> p.id == diary.id }
        if (foundDiary != null) {
            foundDiary.title = diary.title
            foundDiary.description = diary.description
            foundDiary.goals = diary.goals
            foundDiary.assist = diary.assist
            foundDiary.rating = diary.rating
            serialize() // Save changes
            logAll()
        }
    }

    override fun delete(id: String) {
        diaries.removeIf { p -> p.id.toString() == id }
        serialize() // Save changes
        logAll()
    }

    private fun serialize() {
        serializer.serialize(diaries)
    }

    fun logAll() {
        diaries.forEach { i("$it") }
    }
}
