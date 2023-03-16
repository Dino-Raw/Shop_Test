package com.example.onlineshopsatriaadhipradana.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.FlashSaleDomain
import com.example.onlineshopsatriaadhipradana.databinding.ItemFlashSaleBinding
import com.example.onlineshopsatriaadhipradana.presentation.viewholder.HomeFlashSaleViewHolder
import javax.inject.Inject

class HomeFlashSaleAdapter @Inject constructor(): RecyclerView.Adapter<HomeFlashSaleViewHolder>() {
    private val differCallback =
        object : DiffUtil.ItemCallback<FlashSaleDomain>(){
            override fun areItemsTheSame(oldItem: FlashSaleDomain, newItem: FlashSaleDomain): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: FlashSaleDomain, newItem: FlashSaleDomain): Boolean =
                oldItem == newItem
        }

    val differ =
        object : AsyncListDiffer<FlashSaleDomain>(this, differCallback) {
            @SuppressLint("NotifyDataSetChanged")
            override fun submitList(newList: MutableList<FlashSaleDomain>?) {
                super.submitList(newList as MutableList<FlashSaleDomain>)
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFlashSaleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFlashSaleBinding.inflate(layoutInflater, parent, false)
        return HomeFlashSaleViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: HomeFlashSaleViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

}