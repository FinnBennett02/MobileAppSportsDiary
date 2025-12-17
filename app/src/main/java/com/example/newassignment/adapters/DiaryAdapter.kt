package com.example.newassignment.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newassignment.R
import com.example.newassignment.databinding.CardDiaryBinding
import com.example.newassignment.models.DiaryModel
import com.squareup.picasso.Picasso

import kotlin.toString

interface DiaryListener {
    fun onDiaryClick(diary: DiaryModel)
}


class DiaryAdapter(
    private var diaries: List<DiaryModel>,
    private val listener: DiaryListener) :
    RecyclerView.Adapter<DiaryAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDiaryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        // Pass the listener to the MainHolder constructor
        return MainHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val diary = diaries[holder.adapterPosition]
        holder.bind(diary)
    }

    override fun getItemCount(): Int = diaries.size

    fun updateDiaryEnts( diaries: List<DiaryModel>){
        this.diaries = diaries
        notifyDataSetChanged()
    }

    class MainHolder(private val binding: CardDiaryBinding, private val listener: DiaryListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(diary: DiaryModel) {
            binding.placemarkTitle.text = diary.title
            binding.description.text = diary.description
            binding.goals.text = "Goals: ${diary.goals}"
            binding.assists.text = "Assists: ${diary.assist}"
            binding.rating.text = "Rating: ${diary.rating}"

            // ✅ Load image safely
            if (diary.image.isNotEmpty()) {
                Picasso.get()
                    .load(Uri.parse(diary.image))
                    .resize(200, 200)
                    .centerCrop()
                    .into(binding.imageIcon)
            } else {
                // Optional: placeholder if no image
                binding.imageIcon.setImageResource(R.mipmap.ic_launcher)
            }

            binding.root.setOnClickListener { listener.onDiaryClick(diary) }
        }
    }
}
