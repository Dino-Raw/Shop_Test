package com.example.onlineshopsatriaadhipradana.presentation.viewholder

import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopsatriaadhipradana.databinding.ItemProductThumbnailBinding
import com.squareup.picasso.Picasso

class ProductThumbnailViewHolder(val binding: ItemProductThumbnailBinding): RecyclerView.ViewHolder(binding.root) {
    private val widthItem = 66
    private val heightItem = 38
    private val scale = 1.4
    private val elevation = 5
    private val stroke = 1

    fun bind(imageUrl: String, isSelect: Boolean, size: Int) {

        if (isSelect) {
            binding.productThumbnailBackground.updateLayoutParams {
                width = (widthItem * size * scale).toInt()
                height = (heightItem * size * scale).toInt()
            }
            binding.elevation = elevation
            binding.stroke = 0
        } else {
            binding.productThumbnailBackground.updateLayoutParams {
                width = (widthItem * size).toInt()
                height = (heightItem * size).toInt()
            }
            binding.elevation = 0
            binding.stroke = stroke
        }
        Picasso.get()
            .load(imageUrl)
            .into(binding.productThumbnailBackground)
    }
}