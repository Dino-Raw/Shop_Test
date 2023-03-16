package com.example.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductData (
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "rating") val rating: Double,
    @Json(name = "number_of_reviews") val numberOfReviews: Long,
    @Json(name = "price") val price: Double,
    @Json(name = "colors") val colors: List<String>,
    @Json(name = "image_urls") val imageUrls: List<String>,
): java.io.Serializable