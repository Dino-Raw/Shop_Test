package com.example.onlineshopsatriaadhipradana.presentation.viewmodel

import androidx.lifecycle.*
import com.example.domain.model.Response
import com.example.domain.model.UserSignInDomain
import com.example.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
): ViewModel() {
    var firstName: MutableLiveData<String> = MutableLiveData("")
    var lastName: MutableLiveData<String> = MutableLiveData("")
    var email: MutableLiveData<String> = MutableLiveData("")

    private var _isSignIn: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSignIn: LiveData<Boolean> = _isSignIn

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _message: MutableLiveData<String> = MutableLiveData("")
    val message: LiveData<String> = _message

    fun signIn() {
        viewModelScope.launch(Dispatchers.IO) {
            signInUseCase.execute(UserSignInDomain(
                firstName = firstName.value,
                lastName = lastName.value,
                email = email.value,
            )).collect { response -> parseResponse(response) }
        }
    }

    private fun parseResponse(response: Response<Boolean>) {
        when (response) {
            is Response.Loading  -> {
                _isLoading.postValue(true)
            }
            is Response.Success -> {
                _isLoading.postValue(false)
                _isSignIn.postValue(response.data ?: false)
            }
            is Response.Fail -> {
                _isLoading.postValue(false)
                _message.postValue(response.e.message)
            }
            else -> {}
        }
    }
}