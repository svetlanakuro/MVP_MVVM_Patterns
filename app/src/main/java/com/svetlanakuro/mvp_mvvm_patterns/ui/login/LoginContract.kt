package com.svetlanakuro.mvp_mvvm_patterns.ui.login

import com.svetlanakuro.mvp_mvvm_patterns.utils.Publisher

class LoginContract {

//    interface View {
//
//        fun setSuccess(login: String)
//        fun setError(error: String)
//        fun showProgress()
//        fun hideProgress()
//        fun addAccountSuccess(login: String)
//        fun resetPasswordSuccess(password: String)
//    }

    interface ViewModel {

        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String>

        fun onSignIn(login: String, password: String)
        fun onSignUp(login: String, password: String)
        fun onForgotPassword(login: String)
    }
}