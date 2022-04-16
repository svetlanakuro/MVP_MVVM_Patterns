package com.svetlanakuro.mvp_mvvm_patterns

import android.app.Application
import android.content.Context
import com.svetlanakuro.mvp_mvvm_patterns.data.MockLoginApiImpl
import com.svetlanakuro.mvp_mvvm_patterns.domain.LoginApi

class App : Application() {

    val loginApi: LoginApi by lazy { MockLoginApiImpl() }
}

val Context.app: App
    get() {
        return applicationContext as App
    }