package com.example.unittestdemo.mock

import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(userRepository.loginUser(anyString(),anyString())).thenReturn(LOGIN_STATUS.INVALID_PASSWORD)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testUserService() {
        val sut = UserService(userRepository)
        val result = sut.login("email","1234  4545")
        Assert.assertEquals("Invalid password",result)
    }
}