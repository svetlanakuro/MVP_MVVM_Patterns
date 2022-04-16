package com.svetlanakuro.mvp_mvvm_patterns.data

import com.svetlanakuro.mvp_mvvm_patterns.domain.LoginApi

class WebLoginApiImpl : LoginApi {

    override fun signIn(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun signUp(
        login: String, password: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun checkAccount(login: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resetPassword(login: String): String {
        TODO("Not yet implemented")
    }
}