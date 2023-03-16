package com.example.domain.usecase

import com.example.domain.repository.ProductsRepository

class GetSearchProduct(private val repository: ProductsRepository) {
    suspend fun execute(word: String) =
        repository.searchProduct(word = word)
}