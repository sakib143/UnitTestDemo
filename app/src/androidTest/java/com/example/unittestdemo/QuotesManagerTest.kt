package com.example.unittestdemo

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import org.junit.Assert
import org.junit.Test
import java.io.FileNotFoundException

internal class QuotesManagerTest {

    @Test(expected = FileNotFoundException::class)
    fun populate_invalid_file_name() {
        val quotesManager = QuotesManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAsset(context,"")
    }

    @Test
    fun populate_valid_json_file() {
        val quotesManager = QuotesManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAsset(context,"quots.json")
        Assert.assertEquals(4,quotesManager.quoteList.size)
    }

    @Test
    fun get_previous_quotes_expected_correct() {
        //Arrange
        val quotesManager = QuotesManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("First","1"),
                Quote("Second","2"),
                Quote("Third","3")
            )
        )
        //Act
        val quote = quotesManager.getPreviousQuote()
        //Assert
        Assert.assertEquals("1",quote.author)
    }

    @Test
    fun get_next_quote_expected_correct() {
        //Arrange
        val quotesManager = QuotesManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("First","1"),
                Quote("Second","2"),
                Quote("Third","3")
            )
        )
        //Act
        val quote = quotesManager.getNextQuote()
        //Assert
        Assert.assertEquals("2",quote.author)
    }
}