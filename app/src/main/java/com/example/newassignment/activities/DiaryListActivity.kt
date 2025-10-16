package com.example.newassignment.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newassignment.R
import com.example.newassignment.databinding.ActivityDiaryListBinding
import com.example.newassignment.databinding.CardDiaryBinding
import com.example.newassignment.main.MainApp
import com.example.newassignment.models.DiaryModel

class DiaryListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityDiaryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = DiaryAdapter(app.diaries)


    }

}

class DiaryAdapter constructor(private var diaries: List<DiaryModel>) :
                            RecyclerView.Adapter<DiaryAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDiaryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val diary = diaries[holder.adapterPosition]
        holder.bind(diary)
    }
    override fun getItemCount(): Int = diaries.size

    class MainHolder(private val binding : CardDiaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(placemark: DiaryModel) {
            binding.placemarkTitle.text = placemark.title
            binding.description.text = placemark.description
        }
    }
}