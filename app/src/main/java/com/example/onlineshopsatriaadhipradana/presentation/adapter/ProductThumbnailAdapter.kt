package com.example.onlineshopsatriaadhipradana.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopsatriaadhipradana.databinding.ItemProductThumbnailBinding
import com.example.onlineshopsatriaadhipradana.presentation.viewholder.ProductThumbnailViewHolder
import javax.inject.Inject

class ProductThumbnailAdapter @Inject constructor(): RecyclerView.Adapter<ProductThumbnailViewHolder>() {
    var imageUrlList: ArrayList<String> = ArrayList<String>()
    private var selectedPosition: Int = 0
    lateinit var onThumbnailClick: (String) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun updateSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(imageUrlList: ArrayList<String>) {
        this.imageUrlList.clear()
        this.imageUrlList.addAll(imageUrlList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductThumbnailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductThumbnailBinding.inflate(layoutInflater, parent, false)
        return ProductThumbnailViewHolder(binding)
    }

    override fun getItemCount(): Int = imageUrlList.size

    override fun onBindViewHolder(holder: ProductThumbnailViewHolder, position: Int) {
        holder.bind(imageUrlList[position], selectedPosition == position, imageUrlList.size)
        holder.itemView.setOnClickListener { onThumbnailClick.invoke(imageUrlList[position]) }
    }
}