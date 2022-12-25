package com.example.unittestdemo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.unittestdemo.repository.ProductRepository
import com.example.unittestdemo.utils.NetworkResult
import com.example.unittestdemo.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_getProduct() = runTest{

        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Success(emptyList()))
        val sut = MainViewModel(repository)
        sut.getProduct()
        testDispatcher.scheduler.advanceUntilIdle() // added for coroutines
        val result = sut.product.getOrAwaitValue()
        Assert.assertEquals(0,result.data?.size)

    }

    @Test
    fun test_getProduct_expect_error() = runTest {
        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Error("Something went wrong"))
        val sut = MainViewModel(repository)
        sut.getProduct()
        testDispatcher.scheduler.advanceUntilIdle() // added for coroutines
        val result = sut.product.getOrAwaitValue()
        Assert.assertEquals(true,result is NetworkResult.Error)
        Assert.assertEquals("Something went wrong",result.message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}