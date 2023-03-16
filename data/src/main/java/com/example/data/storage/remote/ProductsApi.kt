package com.example.data.storage.remote

import com.example.data.entity.FlashSaleListData
import com.example.data.entity.LatestListData
import com.example.data.entity.ProductData
import com.example.data.entity.SearchListData
import com.example.data.util.API_FLASH_SALE
import com.example.data.util.API_LATEST
import com.example.data.util.API_SEARCH
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ProductsApi {
    @Headers("Accept: application/json")
    @GET(API_FLASH_SALE)
    suspend fun getFlashSaleList(): FlashSaleListData

    @Headers("Accept: application/json")
    @GET(API_LATEST)
    suspend fun getLatestList(): LatestListData

    @Headers("Accept: application/json")
    @GET("{id}")
    suspend fun getProduct(@Path("id") id: String): ProductData

    @Headers("Accept: application/json")
    @GET(API_SEARCH)
    suspend fun searchProduct(): SearchListData
}