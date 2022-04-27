package com.svetlanakuro.mvp_mvvm_patterns.ui.login

class LoginContract {

    interface View {

        fun setSuccess(login: String)
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
        fun addAccountSuccess(login: String)
        fun resetPasswordSuccess(password: String)
    }

    interface Presenter {

        fun onAttach(view: View)
        fun onSignIn(login: String, password: String)
        fun onSignUp(login: String, password: String)
        fun onForgotPassword(login: String)
    }
}