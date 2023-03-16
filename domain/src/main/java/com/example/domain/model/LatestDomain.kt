package com.example.domain.model

data class LatestDomain(
    val category: String,
    val name: String,
    val price: Long,
    val imageUrl: String,
): java.io.Serializable
