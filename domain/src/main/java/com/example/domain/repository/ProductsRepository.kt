package com.example.domain.repository

import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getFlashSaleList(): Flow<Response<ArrayList<FlashSaleDomain>>>
    suspend fun getLatestList(): Flow<Response<ArrayList<LatestDomain>>>
    suspend fun getProduct(id: String): Flow<Response<ProductDomain>>
    suspend fun searchProduct(word: String): Flow<Response<SearchListDomain>>
}