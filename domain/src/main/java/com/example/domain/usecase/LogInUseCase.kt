package com.example.domain.usecase

import com.example.domain.model.Response
import com.example.domain.model.UserLogInDomain
import com.example.domain.model.UserSignInDomain
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LogInUseCase(private val repository: AuthRepository) {
    suspend fun execute(userLogInDomain: UserLogInDomain): Flow<Response<Boolean>> =
        repository.logIn(userLogInDomain = userLogInDomain)
}