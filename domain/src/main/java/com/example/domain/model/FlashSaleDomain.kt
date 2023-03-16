package com.example.domain.model

data class FlashSaleDomain(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Long,
    val imageUrl: String,
): java.io.Serializable

