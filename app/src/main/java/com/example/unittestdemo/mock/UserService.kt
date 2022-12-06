package com.example.unittestdemo.mock

class UserService(private val userRepository: UserRepository) {

    fun login(email: String,password: String): String {
        val status  = userRepository.loginUser(email,password)
        return when (status) {
            LOGIN_STATUS.INVALID_USER -> "User not exist"
            LOGIN_STATUS.SUCCESS -> "Login success"
            LOGIN_STATUS.INVALID_PASSWORD -> "Invalid password"
            else -> {
                ""
            }
        }
    }
}