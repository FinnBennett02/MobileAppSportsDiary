package com.example.newassignment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newassignment.databinding.ActivityDiaryBinding
import com.example.newassignment.models.DiaryModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.Forest.i

class DiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryBinding
    var diaryEntry = DiaryModel()
    var diaryEntrys = ArrayList<DiaryModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.Forest.plant(Timber.DebugTree())
        Timber.Forest.i("Diary Activity started..")


        binding.btnDelete.setOnClickListener() {
            Timber.Forest.i("delete Button Pressed")
        }

        binding.btnAdd.setOnClickListener() {
            diaryEntry.title = binding.diaryEntryTitle.text.toString()
            diaryEntry.description = binding.description.text.toString()


            if (diaryEntry.title.isNotEmpty() && diaryEntry.description.isNotEmpty()) {
                diaryEntrys.add(diaryEntry.copy())
                i("add Button Pressed: $diaryEntry.title")
                for (i in diaryEntrys.indices) {
                    i("Diary Entry[$i]: ${this.diaryEntrys[i]}")
                }
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title and a description", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnUpdate.setOnClickListener() {
            Timber.Forest.i("update Button Pressed")
        }
    }
}