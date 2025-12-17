package com.example.newassignment.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiaryModel(
    var id: Long = 0,
    var title: String = "",
    var description: String = "",
    var goals: Long = 0,
    var assist: Long = 0,
    var rating: Int = 0,
    var image: String = ""
) : Parcelable


