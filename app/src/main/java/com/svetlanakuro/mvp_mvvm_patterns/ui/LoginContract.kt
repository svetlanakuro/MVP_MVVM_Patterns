package com.svetlanakuro.mvp_mvvm_patterns.ui

import androidx.annotation.MainThread

class LoginContract {

    interface View {

        @MainThread
        fun setSuccess(login: String)

        @MainThread
        fun setError(error: String)

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()

        @MainThread
        fun addAccountSuccess(login: String)

        @MainThread
        fun resetPasswordSuccess(password: String)
    }

    interface Presenter {

        fun onAttach(view: View)
        fun onSignIn(login: String, password: String)
        fun onSignUp(login: String, password: String)
        fun onForgotPassword(login: String)
    }

    interface Model {

        fun checkCredentials(login: String, password: String): Boolean
        fun addAccount(login: String, password: String)
        fun checkAccount(login: String): Boolean
        fun resetPassword(login: String): String
    }
}