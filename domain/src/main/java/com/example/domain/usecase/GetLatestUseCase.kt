package com.example.domain.usecase

import com.example.domain.model.LatestDomain
import com.example.domain.model.Response
import com.example.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetLatestUseCase(private val repository: ProductsRepository) {
    suspend fun execute(): Flow<Response<ArrayList<LatestDomain>>> =
        repository.getLatestList()
}