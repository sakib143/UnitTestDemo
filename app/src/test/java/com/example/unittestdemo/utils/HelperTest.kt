package com.example.unittestdemo.utils

import org.junit.Assert
import org.junit.Before
import org.junit.Test


internal class HelperTest {

    lateinit var helper :Helper

    @Before
    fun setup() {
        //Arrange
        helper = Helper()
    }

    @Test
    fun is_input_string_level_return_true() {
        //Act
        val result = helper.isPalindrom("level")
        //Assert
        Assert.assertEquals(true,result)
    }

    @Test
    fun is_not_palindrom_input_string_hello_retur_false() {
        //Act
        val result = helper.isPalindrom("Hello")
        //Assert
        Assert.assertEquals(false,result)
    }

    @Test
    fun is_blank_string_return_true() {
        //Act
        val result = helper.isPalindrom("")
        //Assert
        Assert.assertEquals(true,result)
    }
}