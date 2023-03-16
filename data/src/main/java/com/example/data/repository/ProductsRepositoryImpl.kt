package com.example.data.repository

import android.util.Log
import com.example.data.entity.toDomain
import com.example.data.entity.toDomainList
import com.example.data.storage.remote.ProductsApi
import com.example.domain.model.*
import com.example.domain.repository.ProductsRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.Exception
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi,
): ProductsRepository {
    override suspend fun getFlashSaleList(): Flow<Response<ArrayList<FlashSaleDomain>>> = callbackFlow {
        trySend(Response.Loading())

        try {
            productsApi.getFlashSaleList().toDomainList().also { list ->
                if (list.isNotEmpty()) trySend(Response.Success(data = list))
                else trySend(Response.Fail(e = Exception("Failed to load data")))
            }
        } catch (e: Exception) {
            Log.d("retrofit", e.message.toString())
            trySend(Response.Fail(e = Exception("Failed to load data")))
        }

        awaitClose { cancel() }
    }

    override suspend fun getLatestList(): Flow<Response<ArrayList<LatestDomain>>> = callbackFlow {
        trySend(Response.Loading())

        try {
            productsApi.getLatestList().toDomainList().also { list ->
                if (list.isNotEmpty()) trySend(Response.Success(data = list))
                else trySend(Response.Fail(e = Exception("Failed to load data")))
            }
        } catch (e: Exception) {
            Log.d("retrofit", e.message.toString())
            trySend(Response.Fail(e = Exception("Failed to load data")))
        }

        awaitClose { cancel() }
    }

    override suspend fun getProduct(id: String): Flow<Response<ProductDomain>> = callbackFlow {
        trySend(Response.Loading())

        try {
            productsApi.getProduct(id = id).toDomain().also {  product ->
                trySend(Response.Success(data = product))
            }
        } catch (e: Exception) {
            Log.d("retrofit", e.message.toString())
            trySend(Response.Fail(e = Exception("Failed to load data")))
        }

        awaitClose { cancel() }
    }

    override suspend fun searchProduct(word: String): Flow<Response<SearchListDomain>> = callbackFlow {
        trySend(Response.Loading())

        try {
            if (word != "")
                productsApi.searchProduct().words.filter {
                    it.lowercase().startsWith(word.lowercase())
                }.also { wordList ->
                    trySend(Response.Success(data = SearchListDomain(wordList)))
                }
            else
                trySend(Response.Success(data = SearchListDomain(arrayListOf())))
        } catch (e: Exception) {
            Log.d("retrofit", e.message.toString())
        }

        awaitClose { cancel() }
    }

}