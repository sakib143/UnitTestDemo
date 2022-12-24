package com.example.unittestdemo

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.unittestdemo.adapter.ProductAdapter
import com.example.unittestdemo.model.ProductListItem
import com.example.unittestdemo.utils.NetworkResult
import com.example.unittestdemo.viewmodel.MainViewModel
import com.example.unittestdemo.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var rvProductList: RecyclerView
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProductList = findViewById(R.id.rvProductList)

        val repository = (application as MyApplication).productRepository
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.getProduct()

        mainViewModel.product.observe(this, {
            when(it) {
                is NetworkResult.Success -> {
                    adapter = ProductAdapter(it.data!!)
                    rvProductList.adapter = adapter

                }

                is NetworkResult.Error -> {

                }
                else -> {}
            }
        })

    }
}