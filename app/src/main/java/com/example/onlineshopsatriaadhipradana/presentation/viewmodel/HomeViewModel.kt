package com.example.onlineshopsatriaadhipradana.presentation.viewmodel

import androidx.lifecycle.*
import com.example.domain.model.FlashSaleDomain
import com.example.domain.model.LatestDomain
import com.example.domain.model.Response
import com.example.domain.model.SearchListDomain
import com.example.domain.usecase.GetFlashSaleUseCase
import com.example.domain.usecase.GetLatestUseCase
import com.example.domain.usecase.GetSearchProduct
import com.example.onlineshopsatriaadhipradana.presentation.adapter.HomeFlashSaleAdapter
import com.example.onlineshopsatriaadhipradana.presentation.adapter.HomeLatestAdapter
import com.example.onlineshopsatriaadhipradana.presentation.adapter.HomeSearchAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLatestUseCase: GetLatestUseCase,
    private val getFlashSaleUseCase: GetFlashSaleUseCase,
    private val getSearchProduct: GetSearchProduct,
    val flashSaleAdapter: HomeFlashSaleAdapter,
    val latestAdapter: HomeLatestAdapter,
    val searchAdapter: HomeSearchAdapter,
): ViewModel() {
    var searchWord: MutableLiveData<String> = MutableLiveData("")
    var searchList: MutableLiveData<SearchListDomain?> = MutableLiveData()

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _message: MutableLiveData<String> = MutableLiveData("")
    val message: LiveData<String> = _message

    private var flashSaleList: LiveData<Any> = liveData(Dispatchers.IO) {
        getFlashSaleUseCase.execute().collect { response ->
            parseResponse(response).also { if (it != null) emit(it) }
        }
    }

    private var latestList: LiveData<Any> = liveData(Dispatchers.IO) {
        getLatestUseCase.execute().collect { response ->
            parseResponse(response).also { if (it != null) emit(it) }
        }
    }
    var isDataReceived: MutableLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        value = false
        fun checkResponse(): Boolean = flashSaleList.value != null && latestList.value != null
        addSource(flashSaleList) { this.value = checkResponse() }
        addSource(latestList) {  this.value = checkResponse() }
    }

    fun getSearchProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1_000)
            getSearchProduct.execute(searchWord.value!!).collect { response ->
                if (response is Response.Success) searchList.postValue(response.data)
            }
        }
    }

    private fun parseResponse(response: Response<Any>): Any? {
        when (response) {
            is Response.Success ->
                return response.data
            is Response.Loading ->
                _isLoading.postValue(true)
            is Response.Fail -> {
                _message.postValue(response.e.message)
                _isLoading.postValue(false)
                _message.postValue("")
            }
        }

        return null
    }

    fun setSearchAdapter() {
        searchAdapter.setData(searchList.value?.words as List<String>)
    }

    @Suppress("UNCHECKED_CAST")
    fun setAdapters() {
        flashSaleAdapter.differ.submitList(flashSaleList.value as MutableList<FlashSaleDomain>)
        latestAdapter.differ.submitList(latestList.value as MutableList<LatestDomain>)
        _isLoading.postValue(false)
    }
}