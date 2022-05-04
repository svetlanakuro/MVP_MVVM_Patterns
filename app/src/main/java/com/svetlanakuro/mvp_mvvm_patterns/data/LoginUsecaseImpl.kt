package com.svetlanakuro.mvp_mvvm_patterns.data

import com.svetlanakuro.mvp_mvvm_patterns.domain.*

class LoginUsecaseImpl(
    private val loginApi: LoginApi
) : LoginUsecase {

    override fun signIn(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = loginApi.signIn(login, password)
            callback(result)
        }.start()
    }

    override fun signUp(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = loginApi.signUp(login, password)
            callback(result)
        }.start()
    }

    override fun checkAccount(login: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = loginApi.checkAccount(login)
            callback(result)
        }.start()
    }

    override fun resetPassword(login: String, callback: (String) -> Unit) {
        Thread {
            val result = loginApi.resetPassword(login)
            callback(result)
        }.start()
    }
}