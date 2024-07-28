package com.aherrera.fakelogin.ui.screen.signUp

data class SignUpUiState(
    val user: String? = null,
    val pass: String? = null,
    val enableLoginButton: Boolean = false,
    val alertIsVisible: Boolean = false
)
