package com.svetlanakuro.mvp_mvvm_patterns.ui.login

import com.svetlanakuro.mvp_mvvm_patterns.domain.LoginUsecase
import com.svetlanakuro.mvp_mvvm_patterns.utils.ErrorStrings

class LoginPresenter(
    private val loginUsecase: LoginUsecase
) : LoginContract.Presenter {

    private lateinit var view: LoginContract.View
    private var isSuccess: Boolean = false
    private var errorText: String = ErrorStrings.EMPTY_ERROR.textError
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
                showError(ErrorStrings.INVALID_LOGIN_OR_PASSWORD.textError)
                false
            }
        }
    }

    override fun onSignUp(login: String, password: String) {
        if (login.isBlank() || password.isBlank()) {
            showError(ErrorStrings.EMPTY_FIELDS.textError)
        } else {
            loginUsecase.signUp(login, password) { result ->
                if (result) {
                    view.addAccountSuccess(login)
                } else {
                    showError(ErrorStrings.USER_ALREADY_EXISTS.textError)
                }
            }
        }
    }

    override fun onForgotPassword(login: String) {
        if (login.isBlank()) {
            showError(ErrorStrings.EMPTY_FIELDS.textError)
        } else {
            loginUsecase.checkAccount(login) { result ->
                if (result) {
                    loginUsecase.resetPassword(login) { newPassword ->
                        view.resetPasswordSuccess(newPassword)
                    }
                } else {
                    showError(ErrorStrings.USER_DOES_NOT_EXIST.textError)
                }
            }
        }
    }

    private fun showError(error: String) {
        errorText = error
        view.setError(errorText)
    }
}