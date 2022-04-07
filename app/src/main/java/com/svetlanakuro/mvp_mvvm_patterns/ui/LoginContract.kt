package com.svetlanakuro.mvp_mvvm_patterns.ui

import androidx.annotation.MainThread

class LoginContract {

    interface View {

        @MainThread
        fun setSuccess()

        @MainThread
        fun setError(error: String)

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()
    }

    interface Presenter {

        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
    }
}