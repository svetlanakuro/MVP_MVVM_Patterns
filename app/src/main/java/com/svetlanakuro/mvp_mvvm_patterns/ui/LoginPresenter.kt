package com.svetlanakuro.mvp_mvvm_patterns.ui

import android.os.*
import java.lang.Thread.sleep

class LoginPresenter : LoginContract.Presenter {

    private lateinit var view: LoginContract.View
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess: Boolean = false

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setError("Please, try again.")
        }
    }

    override fun onLogin(login: String, password: String) {
        view.showProgress()
        Thread {
            sleep(3_000)
            uiHandler.post {
                view.hideProgress()
                if (checkCredentials(login, password)) {
                    view.setSuccess()
                } else {
                    view.setError("Invalid password.")
                }
            }

        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        return login == password
    }
}