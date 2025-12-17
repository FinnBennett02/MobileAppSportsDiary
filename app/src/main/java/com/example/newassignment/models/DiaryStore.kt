package com.example.newassignment.models

interface DiaryStore {
    fun findAll(): List<DiaryModel>
    fun create(diary: DiaryModel)
    fun update(diary: DiaryModel)
    fun delete(id: String)
    fun deleteAll()


}


