package com.example.onlineshopsatriaadhipradana.di

import com.example.onlineshopsatriaadhipradana.presentation.fragment.HomeFragment
import com.example.onlineshopsatriaadhipradana.presentation.fragment.LogInFragment
import com.example.onlineshopsatriaadhipradana.presentation.fragment.ProductFragment
import com.example.onlineshopsatriaadhipradana.presentation.fragment.SignInFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(fragment: SignInFragment)
    fun inject(fragment: LogInFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: ProductFragment)
}