package com.example.onlineshopsatriaadhipradana.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Response
import com.example.domain.model.UserLogInDomain
import com.example.domain.usecase.LogInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
): ViewModel() {
    var firstName: MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")

    private var _isLogIn: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLogin: LiveData<Boolean> = _isLogIn

    private var _message: MutableLiveData<String> = MutableLiveData("")
    val message: LiveData<String> = _message

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun logIn() {
        viewModelScope.launch(Dispatchers.IO) {
            logInUseCase.execute(UserLogInDomain(
                firstName = firstName.value,
                password = password.value,
            )).collect { response -> parseResponse(response)}
        }
    }

    private fun parseResponse(response: Response<Boolean>) {
        when (response) {
            is Response.Loading  -> {
                _isLoading.postValue(true)
            }
            is Response.Success -> {
                _isLoading.postValue(false)
                _isLogIn.postValue(response.data ?: false)
            }
            is Response.Fail -> {
                _isLoading.postValue(false)
                _message.postValue(response.e.message)
            }
        }
    }
}