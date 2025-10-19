package com.example.newassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newassignment.databinding.CardDiaryBinding
import com.example.newassignment.models.DiaryModel

interface DiaryListener {
    fun onDiaryClick(diary: DiaryModel)
}

class DiaryAdapter (private var diaries: List<DiaryModel>, private val listener: DiaryListener) :
    RecyclerView.Adapter<DiaryAdapter.MainHolder>() {

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

            fun bind(diary: DiaryModel) {
                binding.placemarkTitle.text = diary.title
                binding.description.text = diary.description
                binding.root.setOnClickListener { listener.onDiaryClick(diary) }
            }
            }
        }
    }