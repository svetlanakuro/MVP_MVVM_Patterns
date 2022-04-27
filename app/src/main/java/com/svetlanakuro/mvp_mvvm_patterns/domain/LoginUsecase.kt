package com.svetlanakuro.mvp_mvvm_patterns.domain

interface LoginUsecase {

    fun signIn(
        login: String, password: String, callback: (Boolean) -> Unit
    )

    fun signUp(
        login: String, password: String, callback: (Boolean) -> Unit
    )

    fun checkAccount(login: String, callback: (Boolean) -> Unit)

    fun resetPassword(login: String, callback: (String) -> Unit)
}