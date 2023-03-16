package com.example.onlineshopsatriaadhipradana.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopsatriaadhipradana.databinding.ItemHomeSearchBinding

class HomeSearchViewHolder(private val binding: ItemHomeSearchBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(product: String) {
        binding.product = product
        binding.executePendingBindings()
    }
}