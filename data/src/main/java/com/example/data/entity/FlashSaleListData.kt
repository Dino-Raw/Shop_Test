package com.example.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlashSaleListData(
    @Json(name = "flash_sale") val flashSale: List<FlashSale>,
): java.io.Serializable {
    @JsonClass(generateAdapter = true)
    data class FlashSale(
        @Json(name = "category") val category: String,
        @Json(name = "name") val name: String,
        @Json(name = "price") val price: Double,
        @Json(name = "discount") val discount: Long,
        @Json(name = "image_url") val imageUrl: String,
    ): java.io.Serializable
}