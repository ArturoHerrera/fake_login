package com.aherrera.fakelogin.ui.screen.login

import com.aherrera.fakelogin.domain.model.UserDetails

data class LoginUiState(
    val user: String? = null,
    val pass: String? = null,
    val enableLoginButton: Boolean = false,
    val alertIsVisible: Boolean = false,
    val alertSuccessIsVisible: Boolean = false,
    val userDetails: UserDetails? = null,
    val isBusy: Boolean = false
)