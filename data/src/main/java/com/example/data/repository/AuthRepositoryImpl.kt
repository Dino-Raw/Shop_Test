package com.example.data.repository

import com.example.data.entity.toData
import com.example.data.storage.local.dao.UserDao
import com.example.domain.model.Response
import com.example.domain.model.UserLogInDomain
import com.example.domain.model.UserSignInDomain
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.regex.Pattern
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : AuthRepository {
    override suspend fun signIn(userSignInDomain: UserSignInDomain): Flow<Response<Boolean>> =
        callbackFlow {
            trySend(Response.Loading())

            if (userSignInDomain.email.isNullOrBlank())
                trySend(Response.Fail(e = Exception("Enter your email address")))
            else if (!isValidEmail(email = userSignInDomain.email!!))
                trySend(Response.Fail(e = Exception("Email address is wrong")))
            else if (userSignInDomain.firstName.isNullOrBlank())
                trySend(Response.Fail(e = Exception("Enter your first name")))
            else if (userSignInDomain.lastName.isNullOrBlank())
                trySend(Response.Fail(e = Exception("Enter your last name")))
            else if (userDao.getUserByEmail(email = userSignInDomain.email!!) != null)
                trySend(Response.Fail(e = Exception("This user already exists")))
            else
                try {
                    userDao.insert(userSignInDomain.toData())
                    trySend(Response.Success(data = true))
                } catch (e: Exception) {
                    trySend(Response.Fail(e = e))
                }

            awaitClose { cancel() }
        }

    override suspend fun logIn(userLogInDomain: UserLogInDomain): Flow<Response<Boolean>> =
        callbackFlow {
            trySend(Response.Loading())

            if (userLogInDomain.firstName.isNullOrBlank())
                trySend(Response.Fail(e = Exception("Enter your first name")))
            else if (userLogInDomain.password.isNullOrBlank())
                trySend(Response.Fail(e = Exception("Enter your password")))
            else if (
                userDao.getUserByFirstNameAndPassword(
                    firstName = userLogInDomain.firstName!!,
                    password = userLogInDomain.password!!,) == null)
                trySend(Response.Fail(e = Exception("Wrong first name or password")))
            else
                trySend(Response.Success(data = true))

            awaitClose { cancel() }
        }

    private fun isValidEmail(email: CharSequence): Boolean {
        Pattern
            .compile("^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE)
            .matcher(email)
            .apply { if (!matches()) return false }

        return true
    }
}