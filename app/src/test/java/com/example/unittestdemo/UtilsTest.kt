package com.example.unittestdemo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UtilsTest {

    @get:Rule
    val mainCoroutineRules = MainCoroutineRules()

    @Test
    fun getGetUser() {
        val sut = Utils(mainCoroutineRules.testDispatcher)
        runTest {
            sut.getUserName()
        }
    }

    @Test
    fun getUserAddress() {
        val sut = Utils(mainCoroutineRules.testDispatcher)
        runTest {
            sut.getAddress()
        }
    }

    @Test
    fun testAddressDetails() {
        val sut = Utils(mainCoroutineRules.testDispatcher)
        runTest {
            sut.getAddressDetails()
            mainCoroutineRules.testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals(true,sut.globalArgs)
        }
    }

}