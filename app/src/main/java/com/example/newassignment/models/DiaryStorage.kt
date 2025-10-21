package com.example.newassignment.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DiaryStorage {
    private const val FILE_NAME = "diaries.json"

    fun saveDiaries(context: Context, diaries: List<DiaryModel>) {
        val json = Gson().toJson(diaries)
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    fun loadDiaries(context: Context): List<DiaryModel> {
        return try {
            val file = context.openFileInput(FILE_NAME)
            val json = file.bufferedReader().readText()
            val type = object : TypeToken<List<DiaryModel>>() {}.type
            Gson().fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
