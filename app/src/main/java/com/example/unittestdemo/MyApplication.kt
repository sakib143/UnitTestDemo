package com.example.unittestdemo

import android.app.Application
import com.example.unittestdemo.api.ProductAPI
import com.example.unittestdemo.repository.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    lateinit var productAPI: ProductAPI
    lateinit var productRepository: ProductRepository


    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()

        productAPI = retrofit.create(ProductAPI::class.java)
        productRepository = ProductRepository(productAPI)

    }

}