package com.example.domain.usecase

import com.example.domain.model.FlashSaleDomain
import com.example.domain.model.Response
import com.example.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetFlashSaleUseCase(private val repository: ProductsRepository) {
    suspend fun execute(): Flow<Response<ArrayList<FlashSaleDomain>>> =
        repository.getFlashSaleList()
}