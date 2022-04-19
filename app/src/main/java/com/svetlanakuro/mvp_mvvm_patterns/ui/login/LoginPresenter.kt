package com.svetlanakuro.mvp_mvvm_patterns.ui.login

import com.svetlanakuro.mvp_mvvm_patterns.domain.LoginUsecase

class LoginPresenter(
    private val loginUsecase: LoginUsecase
) : LoginContract.Presenter {

    companion object {

        private const val EMPTY_ERROR = ""
        private const val INVALID_LOGIN_OR_PASSWORD = "Invalid login or password"
        private const val EMPTY_FIELDS = "Login or password field is empty"
        private const val USER_DOES_NOT_EXIST = "User with this login does not exist"
        private const val USER_ALREADY_EXISTS = "User with this login already exists"
    }

    private lateinit var view: LoginContract.View
    private var isSuccess: Boolean = false
    private var errorText: String = EMPTY_ERROR
    private var currentLogin: String = ""

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess(currentLogin)
        }
    }

    override fun onSignIn(login: String, password: String) {
        view.showProgress()

        loginUsecase.signIn(login, password) { result ->
            view.hideProgress()
            isSuccess = if (result) {
                view.setSuccess(login)
                currentLogin = login
                true
            } else {
                showError(INVALID_LOGIN_OR_PASSWORD)
                false
            }
        }
    }

    override fun onSignUp(login: String, password: String) {
        if (login.isBlank() || password.isBlank()) {
            showError(EMPTY_FIELDS)
        } else {
            loginUsecase.signUp(login, password) { result ->
                if (result) {
                    view.addAccountSuccess(login)
                } else {
                    showError(USER_ALREADY_EXISTS)
                }
            }
        }
    }

    override fun onForgotPassword(login: String) {
        if (login.isBlank()) {
            showError(EMPTY_FIELDS)
        } else {
            loginUsecase.checkAccount(login) { result ->
                if (result) {
                    loginUsecase.resetPassword(login) { newPassword ->
                        view.resetPasswordSuccess(newPassword)
                    }
                } else {
                    showError(USER_DOES_NOT_EXIST)
                }
            }
        }
    }

    private fun showError(error: String) {
        errorText = error
        view.setError(errorText)
    }
}