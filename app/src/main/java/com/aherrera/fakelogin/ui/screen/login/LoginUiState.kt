package com.aherrera.fakelogin.ui.screen.login

data class LoginUiState(
    val user: String? = null,
    val pass: String? = null,
    val enableLoginButton: Boolean = false
)