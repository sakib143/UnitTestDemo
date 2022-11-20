package com.example.unittestdemo.utils

class PasswordValidation {

    fun validatePassword(password: String?) : Boolean {
        password.let {
            if(password?.length!! > 5 && password!!.length < 16)
                return true
        }
        return false
    }


}