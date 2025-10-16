package com.example.newassignment.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.newassignment.databinding.ActivityDiaryBinding
import com.example.newassignment.main.MainApp
import com.example.newassignment.models.DiaryModel
import com.google.android.material.snackbar.Snackbar
import com.example.newassignment.R
import timber.log.Timber
import timber.log.Timber.Forest.i

class DiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryBinding
    var diary = DiaryModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        binding.btnAdd.setOnClickListener() {
            diary.title = binding.diaryEntryTitle.text.toString()
            diary.description = binding.description.text.toString()
            if (diary.title.isNotEmpty()) {
                app.diaries.add(diary.copy())
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_diary, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}
