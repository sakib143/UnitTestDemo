package com.example.unittestdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unittestdemo.model.ProductListItem
import com.example.unittestdemo.repository.ProductRepository
import com.example.unittestdemo.utils.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<NetworkResult<List<ProductListItem?>>>()
    val product :  LiveData<NetworkResult<List<ProductListItem?>>>
    get() = _product

    fun getProduct() {
        viewModelScope.launch {
            val result = repository.getProducts()
            _product.postValue(result)
        }
    }


}