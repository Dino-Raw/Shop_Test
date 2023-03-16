package com.example.onlineshopsatriaadhipradana.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.ProductsRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
interface DataBindModule {
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindProductsRepository(impl: ProductsRepositoryImpl): ProductsRepository
}