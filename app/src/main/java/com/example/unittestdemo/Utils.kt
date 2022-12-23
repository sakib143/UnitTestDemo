package com.example.unittestdemo

import kotlinx.coroutines.*

class Utils(val dispatcher: CoroutineDispatcher) {
    suspend fun getUserName() : String {
        CoroutineScope(Dispatchers.Main).launch {
            delay(10000)
        }
        return "Sakib"
    }

    suspend fun getAddress(): String {
        withContext(dispatcher) { // Here we will use dispather form constructor instead of Dispatchers.IO
            delay(1000)
        }
        return "Address"
    }

    var globalArgs = false
    suspend fun getAddressDetails() {
        CoroutineScope(dispatcher).launch {
            globalArgs = true
        }
    }
}