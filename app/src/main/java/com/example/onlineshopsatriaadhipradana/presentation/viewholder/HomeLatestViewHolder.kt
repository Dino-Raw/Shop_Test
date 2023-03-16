package com.example.onlineshopsatriaadhipradana.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.LatestDomain
import com.example.onlineshopsatriaadhipradana.databinding.ItemLatestBinding
import com.squareup.picasso.Picasso

class HomeLatestViewHolder(private val binding: ItemLatestBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(latest: LatestDomain) {
        if (latest.imageUrl.isNotBlank())
            Picasso.get()
                .load(latest.imageUrl)
                .fit()
                .centerCrop()
                .into(binding.latestBackground)

        binding.name = latest.name
        binding.category = latest.category
        binding.price = "$ ${latest.price},000"
        binding.executePendingBindings()
    }
}