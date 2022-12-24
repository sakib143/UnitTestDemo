package com.example.unittestdemo.api

import com.example.unittestdemo.model.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    @GET("/products")
    suspend fun getProducts() : Response<List<ProductListItem>>

}