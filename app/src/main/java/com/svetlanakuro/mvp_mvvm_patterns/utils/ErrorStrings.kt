package com.svetlanakuro.mvp_mvvm_patterns.utils

enum class ErrorStrings(
    val textError: String
) {
    EMPTY_ERROR(""),
    INVALID_LOGIN_OR_PASSWORD("Invalid login or password"),
    EMPTY_FIELDS("Login or password field is empty"),
    USER_DOES_NOT_EXIST("User with this login does not exist"),
    USER_ALREADY_EXISTS("User with this login already exists")
}