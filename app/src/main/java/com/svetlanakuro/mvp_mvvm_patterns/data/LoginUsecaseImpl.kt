package com.svetlanakuro.mvp_mvvm_patterns.data

import android.os.Handler
import com.svetlanakuro.mvp_mvvm_patterns.domain.*

class LoginUsecaseImpl(
    private val loginApi: LoginApi, private val uiHandler: Handler
) : LoginUsecase {

    override fun signIn(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = loginApi.signIn(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }

    override fun signUp(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = loginApi.signUp(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }

    override fun checkAccount(login: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = loginApi.checkAccount(login)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }

    override fun resetPassword(login: String, callback: (String) -> Unit) {
        Thread {
            val result = loginApi.resetPassword(login)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}