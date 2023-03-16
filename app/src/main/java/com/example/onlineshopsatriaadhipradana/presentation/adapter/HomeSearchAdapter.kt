package com.example.onlineshopsatriaadhipradana.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopsatriaadhipradana.databinding.ItemHomeSearchBinding
import com.example.onlineshopsatriaadhipradana.presentation.viewholder.HomeSearchViewHolder
import javax.inject.Inject

class HomeSearchAdapter @Inject constructor(): RecyclerView.Adapter<HomeSearchViewHolder>() {
    private val productList = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(productList: List<String>) {
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeSearchBinding.inflate(layoutInflater, parent, false)
        return HomeSearchViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: HomeSearchViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}