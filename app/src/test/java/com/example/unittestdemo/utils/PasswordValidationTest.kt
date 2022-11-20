package com.example.unittestdemo.utils

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
internal class PasswordValidationTest (val password: String, val expected: Boolean){

    @Test
    fun test() {
        val passwordValidation = PasswordValidation()
        val result  = passwordValidation.validatePassword(password)
        Assert.assertEquals(expected,result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is password - {1}")
        fun data() : List<Array<Any>> {
            return listOf(
                arrayOf("Hello",false),
                arrayOf("Password",true),
                arrayOf("a",false),
                arrayOf("",false),
                arrayOf("1234567891234",true),
                )
        }
    }

}