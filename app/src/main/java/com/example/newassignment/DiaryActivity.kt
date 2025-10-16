package com.example.newassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newassignment.databinding.ActivityDiaryBinding
import timber.log.Timber
import timber.log.Timber.Forest.i





class DiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("Placemark Activity started..")


        binding.btnDelete.setOnClickListener() {
            i("delete Button Pressed")
        }

        binding.btnAdd.setOnClickListener() {
            i("add Button Pressed")
        }

        binding.btnUpdate.setOnClickListener() {
            i("update Button Pressed")
        }
    }
}