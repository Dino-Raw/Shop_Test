package com.example.domain.model

data class ProductDomain (
    val name: String,
    val description: String,
    val rating: Double,
    val numberOfReviews: Long,
    val price: Double,
    val colors: List<String>,
    val imageUrls: List<String>,
): java.io.Serializable