package com.example.domain.usecase

import com.example.domain.model.ProductDomain
import com.example.domain.model.Response
import com.example.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProductUseCase(private val repository: ProductsRepository) {
    suspend fun execute(id: String): Flow<Response<ProductDomain>> =
        repository.getProduct(id = id)
}