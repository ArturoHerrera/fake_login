package com.aherrera.fakelogin.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aherrera.fakelogin.core.apiResponseHandler.DataState
import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.domain.model.UserDetails
import com.aherrera.fakelogin.domain.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val vmUiState =
        MutableStateFlow(LoginUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    fun login() {
        viewModelScope.launch {
            val currentState = vmUiState.value
            val user = currentState.user
            val pass = currentState.pass

            if (user.isNullOrEmpty() || pass.isNullOrEmpty()) {
                // Previously we validated that the username and password
                // were not null or empty, so this validation is a mere formality.
                setError()
                setIsBusy(false)
            } else {
                loginUseCase
                    .invoke(LoginRequest(user, pass))
                    .collect { result ->
                        when (result) {
                            is DataState.Error -> {
                                setError()
                                setIsBusy(false)

                                //Dummie error validation to copy error case from original app.
                                showLoginAlert()
                            }

                            DataState.Loading -> setIsBusy(true)

                            is DataState.Success -> {
                                setUserDetails(result.data)
                                setIsBusy(false)
                            }

                            else -> Unit
                        }
                    }
            }

        }
    }

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

    fun checkInputsAndEnableButton() {
        val currentState = vmUiState.value
        val canEnableButton =
            !currentState.user.isNullOrEmpty() && !currentState.pass.isNullOrEmpty()
        vmUiState.update { it.copy(enableLoginButton = canEnableButton) }
    }

    fun showLoginAlert() {
        vmUiState.update { it.copy(alertIsVisible = true) }
    }

    fun hideLoginAlert() {
        vmUiState.update { it.copy(alertIsVisible = false) }
    }

    private fun setUserDetails(userDetails: UserDetails) {
        vmUiState.update { it.copy(userDetails = userDetails) }
    }

    private fun setError() {
        //TODO Add error validation, maybe check error types by exceptions.
    }

    private fun setIsBusy(isBusy: Boolean) {
        vmUiState.update { it.copy(isBusy = isBusy) }
    }
}