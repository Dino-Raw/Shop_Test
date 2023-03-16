package com.example.onlineshopsatriaadhipradana.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopsatriaadhipradana.databinding.ItemProductViewpagerBinding
import com.squareup.picasso.Picasso

class ProductViewPagerViewHolder(private val binding: ItemProductViewpagerBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(imageUrl: String) {
        Picasso.get()
            .load(imageUrl)
            .fit()
            .centerCrop()
            .into(binding.productViewpagerBackground)

        binding.executePendingBindings()
    }
}