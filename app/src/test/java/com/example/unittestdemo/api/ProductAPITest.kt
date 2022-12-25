package com.example.unittestdemo.api

import com.example.unittestdemo.repository.ProductRepository
import com.example.unittestdemo.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPITest {

    lateinit var mockWebServer: MockWebServer
    lateinit var productAPI: ProductAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        productAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductAPI::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetProducts_Empty_Array() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()
        mockWebServer.takeRequest()
        Assert.assertEquals(true,response.body()?.isEmpty())
    }

    @Test
    fun testGetProducts_Return_Product() = runTest{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/product_response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()
        mockWebServer.takeRequest()
        Assert.assertEquals(false,response.body()?.isEmpty())
        Assert.assertEquals(2,response.body()?.size)
    }

    @Test
    fun testGetProducts_Return_Error() = runTest{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/product_response.json")
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()
        mockWebServer.takeRequest()
        Assert.assertEquals(false,response.isSuccessful)
        Assert.assertEquals(404,response.code())
    }

}