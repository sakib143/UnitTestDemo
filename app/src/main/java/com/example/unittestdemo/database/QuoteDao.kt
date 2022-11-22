package com.example.unittestdemo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface QuoteDao {
    @Insert
    suspend fun insertQuotes(quote: Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("DELETE FROM quote")
    suspend fun deleteQuote()

    @Query("SELECT * FROM quote")
    fun getQuote () : LiveData<List<Quote>>

}