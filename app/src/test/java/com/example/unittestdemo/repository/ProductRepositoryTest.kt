package com.example.unittestdemo.repository

import com.example.unittestdemo.api.ProductAPI
import com.example.unittestdemo.model.ProductListItem
import com.example.unittestdemo.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {

    @Mock
    lateinit var productAPI: ProductAPI


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetProductList_Empty() = runTest {
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(emptyList()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(0, result.data?.size)
    }

    @Test
    fun testGetProductList_Expected_ProductList() = runTest {
        val productListItem = listOf<ProductListItem>(
            ProductListItem("","","1","",10.0,"Produ 1"),
            ProductListItem("","","2","",50.0,"Produ 2"),
        )
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(productListItem))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(2, result.data?.size)
        Assert.assertEquals("Produ 1",productListItem.get(0).title,)
    }

    @Test
    fun testGetProductList_Expected_Error() = runTest {
        val productListItem = listOf<ProductListItem>(
            ProductListItem("","","1","",10.0,"Produ 1"),
            ProductListItem("","","2","",50.0,"Produ 2"),
        )
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.error(401,"Unauthorized".toResponseBody()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Error)
        Assert.assertEquals("Something went wrong",result.message)
    }


}