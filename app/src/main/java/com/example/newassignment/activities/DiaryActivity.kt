package com.example.newassignment.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.newassignment.databinding.ActivityDiaryBinding
import com.example.newassignment.main.MainApp
import com.example.newassignment.models.DiaryModel
import com.google.android.material.snackbar.Snackbar
import com.example.newassignment.R


class DiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryBinding
    var diary = DiaryModel()
    lateinit var app: MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp


        if (intent.hasExtra("diary_edit")) {
            val receivedDiary =
                IntentCompat.getParcelableExtra(intent, "diary_edit", DiaryModel::class.java)
            if (receivedDiary != null) {
                edit = true
                diary = receivedDiary
                binding.diaryEntryTitle.setText(diary.title)
                binding.description.setText(diary.description)
                binding.btnAdd.setText(R.string.save_diary)
            }
        }


        binding.btnAdd.setOnClickListener() {
            diary.title = binding.diaryEntryTitle.text.toString()
            diary.description = binding.description.text.toString()
            if (diary.title.isEmpty()) {
                Snackbar.make(it, getString(R.string.no_title), Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.diaries.update(diary.copy())
                } else {
                    app.diaries.create(diary.copy())
                }
                setResult(RESULT_OK)
                finish()
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

