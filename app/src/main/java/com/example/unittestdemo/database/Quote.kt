package com.example.unittestdemo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Quote(
    @PrimaryKey(autoGenerate = true) val id: Int, val text: String, val author: String
)