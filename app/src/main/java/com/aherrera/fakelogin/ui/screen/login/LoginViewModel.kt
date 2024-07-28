package com.aherrera.fakelogin.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val vmUiState =
        MutableStateFlow(LoginUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    fun setUser(newUser: String?) {
        newUser?.let { safeNewUser ->
            if (safeNewUser.isNotEmpty()) {
                vmUiState.update { it.copy(user = newUser) }
            }
        }
    }

    fun setPass(newPass: String?) {
        newPass?.let { safeNewPass ->
            if (safeNewPass.isNotEmpty()) {
                vmUiState.update { it.copy(pass = newPass) }
            }
        }
    }

    fun checkInputsAndEnableButton(){
        val currentState = vmUiState.value
        val canEnableButton = !currentState.user.isNullOrEmpty() && !currentState.pass.isNullOrEmpty()
        vmUiState.update { it.copy(enableLoginButton = canEnableButton) }
    }

}