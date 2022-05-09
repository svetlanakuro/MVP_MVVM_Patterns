package com.svetlanakuro.mvp_mvvm_patterns.ui.login

import com.svetlanakuro.mvp_mvvm_patterns.domain.LoginUsecase
import com.svetlanakuro.mvp_mvvm_patterns.utils.*

class LoginViewModel(
    private val loginUsecase: LoginUsecase
) : LoginContract.ViewModel {

    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<String> = Publisher()
    override val errorText: Publisher<String> = Publisher()
    override val addAccountSuccess: Publisher<String> = Publisher()
    override val resetPasswordSuccess: Publisher<String> = Publisher()

    private var currentLogin: String = ""

    override fun onSignIn(login: String, password: String) {
        shouldShowProgress.post(true)

        loginUsecase.signIn(login, password) { result ->
            shouldShowProgress.post(false)

            if (result) {
                isSuccess.post(login)
                currentLogin = login
            } else {
                errorText.post(ErrorStrings.INVALID_LOGIN_OR_PASSWORD.textError)
            }
        }
    }

    override fun onSignUp(login: String, password: String) {
        if (login.isBlank() || password.isBlank()) {
            errorText.post(ErrorStrings.EMPTY_FIELDS.textError)
        } else {
            loginUsecase.signUp(login, password) { result ->
                if (result) {
                    addAccountSuccess.post(login)
                } else {
                    errorText.post(ErrorStrings.USER_ALREADY_EXISTS.textError)
                }
            }
        }
    }

    override fun onForgotPassword(login: String) {
        if (login.isBlank()) {
            errorText.post(ErrorStrings.EMPTY_FIELDS.textError)
        } else {
            loginUsecase.checkAccount(login) { result ->
                if (result) {
                    loginUsecase.resetPassword(login) { newPassword ->
                        resetPasswordSuccess.post(newPassword)
                    }
                } else {
                    errorText.post(ErrorStrings.USER_DOES_NOT_EXIST.textError)
                }
            }
        }
    }
}