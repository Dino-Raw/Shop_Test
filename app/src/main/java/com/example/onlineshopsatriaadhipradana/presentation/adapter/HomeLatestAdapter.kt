package com.example.onlineshopsatriaadhipradana.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.LatestDomain
import com.example.onlineshopsatriaadhipradana.databinding.ItemLatestBinding
import com.example.onlineshopsatriaadhipradana.presentation.viewholder.HomeLatestViewHolder
import javax.inject.Inject

class HomeLatestAdapter @Inject constructor(): RecyclerView.Adapter<HomeLatestViewHolder>() {
    private val differCallback =
        object : DiffUtil.ItemCallback<LatestDomain>(){
            override fun areItemsTheSame(oldItem: LatestDomain, newItem: LatestDomain): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: LatestDomain, newItem: LatestDomain): Boolean =
                oldItem == newItem
        }

    val differ =
        object : AsyncListDiffer<LatestDomain>(this, differCallback) {
            @SuppressLint("NotifyDataSetChanged")
            override fun submitList(newList: MutableList<LatestDomain>?) {
                super.submitList(newList as MutableList<LatestDomain>)
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeLatestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLatestBinding.inflate(layoutInflater, parent, false)
        return HomeLatestViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: HomeLatestViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}