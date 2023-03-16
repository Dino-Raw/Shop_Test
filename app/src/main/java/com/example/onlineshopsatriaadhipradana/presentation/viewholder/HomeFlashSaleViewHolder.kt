package com.example.onlineshopsatriaadhipradana.presentation.viewholder

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.FlashSaleDomain
import com.example.onlineshopsatriaadhipradana.R
import com.example.onlineshopsatriaadhipradana.databinding.ItemFlashSaleBinding
import com.example.onlineshopsatriaadhipradana.util.ID_REEBOK
import com.squareup.picasso.Picasso

class HomeFlashSaleViewHolder(private val binding: ItemFlashSaleBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(flashSale: FlashSaleDomain) {
        if (flashSale.imageUrl.isNotBlank())
            Picasso.get()
                .load(flashSale.imageUrl)
                .fit()
                .centerCrop()
                .into(binding.flashSaleBackground)

        binding.name = flashSale.name
        binding.category = flashSale.category
        binding.price = "$ ${flashSale.price}0"
        binding.discount = "${flashSale.discount}% off"


        binding.root.setOnClickListener {
            // Для других элементов нет ссылок
            if (flashSale.name == "Reebok Classic")
                binding.root.findNavController().navigate (
                    R.id.action_fragment_home_to_fragment_product,
                    bundleOf("id" to ID_REEBOK)
                )
        }

        binding.executePendingBindings()
    }
}