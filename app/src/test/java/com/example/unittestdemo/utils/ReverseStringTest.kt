package com.example.unittestdemo.utils

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter

@RunWith(value = Parameterized::class)
internal class ReverseStringTest(val input: String, val expected: Any) {

    @Test
    fun test() {
        val reverseString = ReverseString()
        val result = reverseString.getReverseString(input)
        Assert.assertEquals(expected,result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data() : List<Array<Any?>> {
            return listOf(
                arrayOf("abc","cba"),
                arrayOf("",""),
                )
        }
    }

}