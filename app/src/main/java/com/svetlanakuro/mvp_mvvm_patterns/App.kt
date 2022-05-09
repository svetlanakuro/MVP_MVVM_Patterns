package com.svetlanakuro.mvp_mvvm_patterns

import android.app.Application
import android.content.Context
import com.svetlanakuro.mvp_mvvm_patterns.data.*
import com.svetlanakuro.mvp_mvvm_patterns.domain.*

class App : Application() {

    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }

    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(app.loginApi)
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }