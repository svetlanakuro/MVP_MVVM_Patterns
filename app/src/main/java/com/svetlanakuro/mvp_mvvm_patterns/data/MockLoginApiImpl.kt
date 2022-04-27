package com.svetlanakuro.mvp_mvvm_patterns.data

import com.svetlanakuro.mvp_mvvm_patterns.domain.LoginApi
import com.svetlanakuro.mvp_mvvm_patterns.domain.entities.UserProfile

class MockLoginApiImpl : LoginApi {

    private val mockUserProfileRepo = mutableSetOf(
        UserProfile("admin", "admin"),
        UserProfile("user", "user"),
    )

    override fun signIn(login: String, password: String): Boolean {
        mockUserProfileRepo.forEach { user ->
            if (user.login == login && user.password == password) return true
        }
        return false
    }

    override fun signUp(
        login: String, password: String
    ): Boolean {
        return if (!checkAccount(login)) {
            mockUserProfileRepo.add(UserProfile(login, password))
            true
        } else {
            false
        }
    }

    override fun checkAccount(login: String): Boolean {
        mockUserProfileRepo.forEach { user ->
            if (user.login == login) return true
        }
        return false
    }

    override fun resetPassword(login: String): String {
        mockUserProfileRepo.forEach { user ->
            if (user.login == login) {
                user.password = (1000 until 2000).random().toString()
                return user.password
            }
        }
        return ""
    }
}