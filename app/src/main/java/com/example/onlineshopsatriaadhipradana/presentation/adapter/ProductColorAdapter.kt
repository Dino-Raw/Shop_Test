package com.example.onlineshopsatriaadhipradana.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopsatriaadhipradana.databinding.ItemProductColorBinding
import com.example.onlineshopsatriaadhipradana.presentation.viewholder.ProductColorViewHolder
import javax.inject.Inject

class ProductColorAdapter @Inject constructor(): RecyclerView.Adapter<ProductColorViewHolder>() {
    var colorList: ArrayList<String> = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(imageUrlList: ArrayList<String>) {
        this.colorList.clear()
        this.colorList.addAll(imageUrlList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductColorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductColorBinding.inflate(layoutInflater, parent, false)
        return ProductColorViewHolder(binding)
    }

    override fun getItemCount(): Int = colorList.size

    override fun onBindViewHolder(holder: ProductColorViewHolder, position: Int) {
        holder.bind(colorList[position])
    }
}