package com.example.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchListData(
    @Json(name = "words") var words: List<String>,
)
