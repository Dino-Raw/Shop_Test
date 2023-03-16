package com.example.domain.repository

import com.example.domain.model.Response
import com.example.domain.model.UserLogInDomain
import com.example.domain.model.UserSignInDomain
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(userSignInDomain: UserSignInDomain): Flow<Response<Boolean>>
    suspend fun logIn(userLogInDomain: UserLogInDomain): Flow<Response<Boolean>>
}