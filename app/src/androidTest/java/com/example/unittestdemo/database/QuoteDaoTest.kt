package com.example.unittestdemo.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.unittestdemo.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuoteDaoTest {

    lateinit var quoteDabae : QuoteDatabase
    lateinit var quoteDao: QuoteDao

    @get:Rule
    val instanceExecutorRule = InstantTaskExecutorRule() // It will executer all archetecture component to syncronously execute

    @Before
    fun setup() {
        quoteDabae = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()
        quoteDao = quoteDabae.quoteDao()
    }

    @After
    fun tearDown() {
        quoteDabae.close()
    }

    //runBlocking will block the thread and execute only one thread untill testing will get completed
    @Test
    fun insertQuote_expected_single_Quote() = runBlocking {
        val quote = Quote(1,"first","sakib")
        quoteDao.insertQuotes(quote)
        //getOrAwaitValue() extension function and will block the thead until live data response not received
        val result = quoteDao.getQuote().getOrAwaitValue()
        Assert.assertEquals(1,result.size)
    }

    @Test
    fun delete_from_database()  = runBlocking {
        val quote = Quote(1,"first","sakib")
        quoteDao.insertQuotes(quote)
        quoteDao.deleteQuote()
        val result = quoteDao.getQuote().getOrAwaitValue()
        Assert.assertEquals(0,result.size)
    }
}