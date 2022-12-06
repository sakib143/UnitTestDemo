package com.example.unittestdemo.mock

class UserRepository {

    val alUsers = listOf<User>(
        User(1,"Sakib","email","1234"),
        User(2,"Test 1","email","1234"),
        User(3,"Test 2","email","1234")
        )

    fun loginUser(email: String, password: String) : LOGIN_STATUS {
        val userArray = alUsers.filter { it -> it.email == email  }
        return if (userArray.size == 1) {
            if(userArray.get(0).password == password) {
                LOGIN_STATUS.SUCCESS
            } else {
                LOGIN_STATUS.INVALID_PASSWORD
            }
        } else {
            LOGIN_STATUS.INVALID_USER
        }
    }
}