package com.svetlanakuro.mvp_mvvm_patterns.data

import com.svetlanakuro.mvp_mvvm_patterns.domain.LoginApi

class MockLoginApiImpl : LoginApi {

    private val accounts = mutableMapOf(
        "admin" to "admin", "user" to "user"
    )

    override fun signIn(login: String, password: String): Boolean {
        for ((key, value) in accounts) {
            if (key == login && value == password) {
                return true
            }
        }
        return false
    }

    override fun signUp(
        login: String, password: String
    ): Boolean {
        return if (!checkAccount(login)) {
            accounts[login] = password
            true
        } else {
            false
        }
    }

    override fun checkAccount(login: String): Boolean {
        return accounts.containsKey(login)
    }

    override fun resetPassword(login: String): String {
        accounts[login] = (1000 until 2000).random().toString()
        return accounts[login].toString()
    }
}