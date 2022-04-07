package com.svetlanakuro.mvp_mvvm_patterns.domain

import com.svetlanakuro.mvp_mvvm_patterns.ui.LoginContract

class LoginModel : LoginContract.Model {

    private val accounts = mutableMapOf(
        "admin" to "admin", "user" to "user"
    )

    override fun checkCredentials(login: String, password: String): Boolean {
        for ((key, value) in accounts) {
            if (key == login && value == password) {
                return true
            }
        }
        return false
    }

    override fun addAccount(login: String, password: String) {
        accounts[login] = password
    }

    override fun checkAccount(login: String): Boolean {
        return accounts.containsKey(login)
    }

    override fun resetPassword(login: String): String {
        accounts[login] = (1000 until 2000).random().toString()
        return accounts[login].toString()
    }
}