package com.example.domain.usecase

import com.example.domain.model.Response
import com.example.domain.model.UserSignInDomain
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class SignInUseCase(private val repository: AuthRepository) {
    suspend fun execute(userSignInDomain: UserSignInDomain): Flow<Response<Boolean>> =
        repository.signIn(userSignInDomain = userSignInDomain)
}