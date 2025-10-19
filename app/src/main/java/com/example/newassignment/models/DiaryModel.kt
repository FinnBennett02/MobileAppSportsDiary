package com.example.newassignment.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiaryModel(var title: String ="", var description: String ="")
