package com.example.onlineshopsatriaadhipradana.di

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProductsRepository
import com.example.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideSignInUseCase(repository: AuthRepository) =
        SignInUseCase(repository = repository)

    @Provides
    fun provideLogInUseCase(repository: AuthRepository) =
        LogInUseCase(repository = repository)

    @Provides
    fun provideGetFlashSaleUseCase(repository: ProductsRepository) =
        GetFlashSaleUseCase(repository = repository)

    @Provides
    fun provideGetLatestUseCase(repository: ProductsRepository) =
        GetLatestUseCase(repository = repository)

    @Provides
    fun provideGetProductUseCase(repository: ProductsRepository) =
        GetProductUseCase(repository = repository)

    @Provides
    fun provideGetSearchProduct(repository: ProductsRepository) =
        GetSearchProduct(repository = repository)
}