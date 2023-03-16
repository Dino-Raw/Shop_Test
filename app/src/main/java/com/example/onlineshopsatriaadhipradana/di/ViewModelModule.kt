package com.example.onlineshopsatriaadhipradana.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopsatriaadhipradana.presentation.viewmodel.HomeViewModel
import com.example.onlineshopsatriaadhipradana.presentation.viewmodel.LogInViewModel
import com.example.onlineshopsatriaadhipradana.presentation.viewmodel.ProductViewModel
import com.example.onlineshopsatriaadhipradana.presentation.viewmodel.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun bindSignInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    fun bindLogInViewModel(logInViewModel: LogInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    fun bindProductViewModel(productViewModel: ProductViewModel): ViewModel
}