package com.example.newassignment.models

interface DiaryStore {
    fun findAll(): List<DiaryModel>
    fun create(diary: DiaryModel)
}


