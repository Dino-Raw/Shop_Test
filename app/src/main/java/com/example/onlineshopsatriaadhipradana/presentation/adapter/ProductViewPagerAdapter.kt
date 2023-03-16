package com.example.onlineshopsatriaadhipradana.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopsatriaadhipradana.databinding.ItemProductViewpagerBinding
import com.example.onlineshopsatriaadhipradana.presentation.viewholder.ProductViewPagerViewHolder
import javax.inject.Inject

class ProductViewPagerAdapter @Inject constructor(): RecyclerView.Adapter<ProductViewPagerViewHolder>() {
    var imageUrlList: ArrayList<String> = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(imageUrlList: ArrayList<String>) {
        this.imageUrlList.clear()
        this.imageUrlList.addAll(imageUrlList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductViewpagerBinding.inflate(layoutInflater, parent, false)
        return ProductViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int = imageUrlList.size

    override fun onBindViewHolder(holder: ProductViewPagerViewHolder, position: Int) {
        holder.bind(imageUrlList[position])
    }
}