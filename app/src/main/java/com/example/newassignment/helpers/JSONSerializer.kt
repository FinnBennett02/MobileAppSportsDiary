
package com.example.newassignment.helpers

import android.content.Context
import com.example.newassignment.models.DiaryModel

import com.google.gson.Gson


import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.*
import java.lang.reflect.Type


private const val JSON_FILE = "diaries.json"

class JSONSerializer(private val context: Context) {

    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    private val file = context.getFileStreamPath(JSON_FILE)
    private val listType: Type = object : TypeToken<ArrayList<DiaryModel>>() {}.type


    fun serialize(diaries: List<DiaryModel>) {
        try {
            val jsonString = gson.toJson(diaries, listType)
            val writer = FileWriter(file)
            writer.write(jsonString)
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    fun deserialize(): List<DiaryModel> {
        if (!file.exists()) {

            return emptyList()
        }

        try {
            val jsonString = file.reader().readText()
            if (jsonString.isEmpty()) {
                return emptyList()
            }
            return gson.fromJson(jsonString, listType)
        } catch (e: IOException) {
            e.printStackTrace()

            return emptyList()
        }
    }
}
