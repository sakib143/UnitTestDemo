package com.example.unittestdemo

import android.content.Context
import androidx.core.graphics.green
import com.google.gson.Gson
import java.nio.charset.Charset

class QuotesManager {
    var quoteList = emptyArray<Quote>()
    var currentPosition = 0

    fun populateQuoteFromAsset(contex: Context, fileName: String) {
        val inputString = contex.assets.open(fileName)
        val size: Int = inputString.available()
        val buffer = ByteArray(size)
        inputString.read(buffer)
        inputString.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json,Array<Quote>::class.java)
    }

    fun populateQuotes(quotes: Array<Quote>) {
        quoteList = quotes
    }

    fun getCurrentQuote() : Quote {
        return  quoteList.get(currentPosition)
    }

    fun getNextQuote(): Quote {
        if(currentPosition == quoteList.size -1) return quoteList.get(currentPosition)
        return quoteList.get(++currentPosition)
    }

    fun getPreviousQuote() : Quote {
        if(currentPosition == 0) return quoteList.get(currentPosition)
        return quoteList.get(--currentPosition)

    }

}