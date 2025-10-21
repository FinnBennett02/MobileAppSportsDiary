package com.example.newassignment.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newassignment.R
import com.example.newassignment.adapters.DiaryAdapter
import com.example.newassignment.adapters.DiaryListener
import com.example.newassignment.databinding.ActivityDiaryListBinding
import com.example.newassignment.main.MainApp
import com.example.newassignment.models.DiaryModel

class DiaryListActivity : AppCompatActivity(), DiaryListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityDiaryListBinding
    private lateinit var adapter: DiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager


        adapter = DiaryAdapter(app.diaries.findAll(), this)


        binding.recyclerView.adapter = adapter


        binding.editText.addTextChangedListener { diaryFilter ->
            val filteredDiaries = app.diaries.findAll().filter { diary ->
                diary.title.lowercase().contains(diaryFilter.toString().lowercase())
            }
            adapter.updateDiaryEnts(filteredDiaries)
        }



        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, DiaryActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                adapter.updateDiaryEnts(app.diaries.findAll())
                binding.editText.text.clear()
            }
        }

    override fun onDiaryClick(diary: DiaryModel) {
        val launcherIntent = Intent(this, DiaryActivity::class.java)
        launcherIntent.putExtra("diary_edit", diary)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                // Also refresh correctly here
                adapter.updateDiaryEnts(app.diaries.findAll())
                binding.editText.text.clear()
            }
        }
}
