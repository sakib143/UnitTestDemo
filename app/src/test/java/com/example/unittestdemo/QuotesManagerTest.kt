package com.example.unittestdemo

import android.content.Context
import android.content.res.AssetManager
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class QuotesManagerTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var assetManager: AssetManager

    @Before
    fun setup() {
    MockitoAnnotations.openMocks(this)
    }

    @Test
    fun  test() {
        val testQuote = QuotesManagerTest::class.java.getResourceAsStream("/quotes.json")
        //Here we have mapped context with this asset manager
        doReturn(assetManager).`when`(context).assets
        Mockito.`when`(context.assets.open(anyString())).thenReturn(testQuote)

        val sut = QuotesManager()
        sut.populateQuoteFromAsset(context,"")
        val quote = sut.getCurrentQuote()
        Assert.assertEquals("Well begun is half done",quote.text)





    }
}