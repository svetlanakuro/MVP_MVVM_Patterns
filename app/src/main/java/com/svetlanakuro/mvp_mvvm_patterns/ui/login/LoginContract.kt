package com.svetlanakuro.mvp_mvvm_patterns.ui.login

import com.svetlanakuro.mvp_mvvm_patterns.utils.Publisher

class LoginContract {

    interface ViewModel {

        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<String>
        val errorText: Publisher<String>
        val addAccountSuccess: Publisher<String>
        val resetPasswordSuccess: Publisher<String>

        fun onSignIn(login: String, password: String)
        fun onSignUp(login: String, password: String)
        fun onForgotPassword(login: String)
    }
}