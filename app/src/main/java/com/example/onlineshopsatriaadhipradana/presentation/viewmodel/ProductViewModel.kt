package com.example.onlineshopsatriaadhipradana.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProductDomain
import com.example.domain.model.Response
import com.example.domain.usecase.GetProductUseCase
import com.example.onlineshopsatriaadhipradana.presentation.adapter.ProductColorAdapter
import com.example.onlineshopsatriaadhipradana.presentation.adapter.ProductThumbnailAdapter
import com.example.onlineshopsatriaadhipradana.presentation.adapter.ProductViewPagerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    val productViewPagerAdapter: ProductViewPagerAdapter,
    val productThumbnailAdapter: ProductThumbnailAdapter,
    val colorAdapter: ProductColorAdapter,
): ViewModel() {
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _message: MutableLiveData<String> = MutableLiveData("")
    val message: LiveData<String> = _message

    private var _product: MutableLiveData<ProductDomain?> = MutableLiveData()
    val product: LiveData<ProductDomain?> = _product

    private var _numberOfProducts: MutableLiveData<Int> = MutableLiveData(0)
    val numberOfProducts: LiveData<Int> = _numberOfProducts

    fun initProduct(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getProductUseCase.execute(id).collect { response -> parseResponse(response) }
        }
    }

    fun setAdapters() {
        productViewPagerAdapter.setData(_product.value?.imageUrls as ArrayList<String>)
        productThumbnailAdapter.setData(_product.value?.imageUrls as ArrayList<String>)
        colorAdapter.setData(_product.value?.colors as ArrayList<String>)
    }

    fun increase() {
        _numberOfProducts.value = _numberOfProducts.value?.plus(1)
    }

    fun decrease() {
        if (_numberOfProducts.value!! > 0)
            _numberOfProducts.value = _numberOfProducts.value?.minus(1)
    }

    private fun parseResponse(response: Response<ProductDomain>) {
        when (response) {
            is Response.Success -> {
                _product.postValue(response.data)
                _isLoading.postValue(false)
            }
            is Response.Loading -> {
                _isLoading.postValue(true)
            }
            is Response.Fail -> {
                _isLoading.postValue(false)
                _message.postValue(response.e.message)
                _message.postValue("")
            }
        }
    }
}