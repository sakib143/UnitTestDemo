package com.example.unittestdemo.repository

import com.example.unittestdemo.api.ProductAPI
import com.example.unittestdemo.model.ProductListItem
import com.example.unittestdemo.utils.NetworkResult

class ProductRepository(private val productAPI: ProductAPI) {

    suspend fun getProducts(): NetworkResult<List<ProductListItem?>> {
        val response = productAPI.getProducts()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if(responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error("Something went wrong")
            }
        } else {
            NetworkResult.Error("Something went wrong")
        }

    }
}