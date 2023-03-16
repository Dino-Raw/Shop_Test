package com.example.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestListData(
    @Json(name = "latest") val latest: List<Latest>
): java.io.Serializable {
    @JsonClass(generateAdapter = true)
    data class Latest (
        @Json(name = "category") val category: String,
        @Json(name = "name") val name: String,
        @Json(name = "price") val price: Long,
        @Json(name = "image_url") val imageUrl: String,
    ): java.io.Serializable
}
