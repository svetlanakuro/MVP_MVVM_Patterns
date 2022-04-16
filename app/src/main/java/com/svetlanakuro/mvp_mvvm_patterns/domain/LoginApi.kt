package com.svetlanakuro.mvp_mvvm_patterns.domain

interface LoginApi {

    fun signIn(login: String, password: String): Boolean
    fun signUp(login: String, password: String): Boolean
    fun checkAccount(login: String): Boolean
    fun resetPassword(login: String): String

}