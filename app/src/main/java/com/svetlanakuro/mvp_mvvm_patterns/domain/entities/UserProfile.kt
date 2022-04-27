package com.svetlanakuro.mvp_mvvm_patterns.domain.entities

data class UserProfile(
    var login: String,
    var password: String,
    var email: String = "default email@gmail.com",
    var avatarUrl: String = "https://default_avatar.com"
) {

    val id: Int = 0
}