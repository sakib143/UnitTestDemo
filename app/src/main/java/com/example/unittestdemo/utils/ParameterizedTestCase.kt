package com.example.unittestdemo.utils

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ParameterizedTestCase(val input: String, val expectedValue : Boolean) {

    @Test
    fun test() {
        val helper = Helper()
        val result  = helper.isPalindrom(input)
        Assert.assertEquals(result,expectedValue)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is pallindrom - {1}")
        fun data() : List<Array<Any>> {
            return listOf(
                arrayOf("Hello",false),
                arrayOf("level",true),
                arrayOf("a",true),
                arrayOf("",true),
                )
        }
    }
}