package com.example.onlineshopsatriaadhipradana.presentation.viewholder

import android.graphics.Color
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopsatriaadhipradana.databinding.ItemProductColorBinding

class ProductColorViewHolder(private val binding: ItemProductColorBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(color: String) {
        binding.stroke = if (color == "#ffffff")  2 else 0
        binding.productColorBackground.setBackgroundColor(Color.parseColor(color))

        binding.root.setOnClickListener {
            Toast.makeText(binding.root.context, color, Toast.LENGTH_SHORT).show()
        }
    }
}