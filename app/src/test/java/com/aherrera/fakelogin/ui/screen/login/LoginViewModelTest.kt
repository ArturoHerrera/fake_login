package com.aherrera.fakelogin.ui.screen.login

import com.aherrera.fakelogin.core.apiResponseHandler.BaseError
import com.aherrera.fakelogin.core.apiResponseHandler.DataState
import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.domain.model.UserDetails
import com.aherrera.fakelogin.domain.useCase.LoginUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginUseCase: LoginUseCase

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        loginUseCase = mock(LoginUseCase::class.java)
        loginViewModel = LoginViewModel(loginUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Resetea el Main dispatcher
    }

    @Test
    fun `test login success`() = runTest {
        // Arrange
        val request = LoginRequest(username = "emilys", password = "emilyspass")
        val response = UserDetails(fullName = "Emily Johnson", avatarUrl = "https://dummyjson.com/icon/emilys/128")
        val flow: Flow<DataState<UserDetails>> = flow {
            emit(DataState.Loading)
            emit(DataState.Success(response))
        }
        `when`(loginUseCase.invoke(request)).thenReturn(flow)

        // Act
        loginViewModel.setUser("emilys")
        loginViewModel.setPass("emilyspass")
        loginViewModel.login()

        // Assert
        val uiState = loginViewModel.uiState.value
        assertEquals("emilys", uiState.user)
        assertEquals("emilyspass", uiState.pass)

        runCurrent() // Advance the coroutine to the next point of suspension

        val uiStateAfterSuccess = loginViewModel.uiState.value
        assertFalse(uiStateAfterSuccess.isBusy) // Should not be busy after success
        assertEquals("Emily Johnson", uiStateAfterSuccess.userDetails?.fullName)
        assertEquals("https://dummyjson.com/icon/emilys/128", uiStateAfterSuccess.userDetails?.avatarUrl)
        assertTrue(uiStateAfterSuccess.alertSuccessIsVisible)
    }

    @Test
    fun `test login error`() = runTest {
        // Arrange
        val request = LoginRequest(username = "testuser", password = "testpass")
        val error = BaseError(localMessage = "Error occurred")
        val flow: Flow<DataState<UserDetails>> = flow {
            emit(DataState.Loading)
            emit(DataState.Error(error))
        }
        `when`(loginUseCase.invoke(request)).thenReturn(flow)

        // Act
        loginViewModel.setUser("testuser")
        loginViewModel.setPass("testpass")
        loginViewModel.login()

        runCurrent() // Advance the coroutine to the next point of suspension

        val uiStateAfterError = loginViewModel.uiState.value
        assertFalse(uiStateAfterError.isBusy) // Should not be busy after error
        assertNull(uiStateAfterError.userDetails)
        assertTrue(uiStateAfterError.alertIsVisible)
    }

    @Test
    fun `test setUser and setPass`() = runTest {
        // Act
        loginViewModel.setUser("testuser")
        loginViewModel.setPass("testpass")

        // Assert
        val uiState = loginViewModel.uiState.value
        assertEquals("testuser", uiState.user)
        assertEquals("testpass", uiState.pass)
    }

    @Test
    fun `test checkInputsAndEnableButton with valid inputs`() = runTest {
        // Act
        loginViewModel.setUser("emilys")
        loginViewModel.setPass("emilyspass")
        loginViewModel.checkInputsAndEnableButton()

        // Assert
        val uiState = loginViewModel.uiState.value
        assertTrue(uiState.enableLoginButton)
    }

    @Test
    fun `test checkInputsAndEnableButton with invalid inputs`() = runTest {
        // Act
        loginViewModel.setUser("emilys")
        loginViewModel.setPass("")
        loginViewModel.checkInputsAndEnableButton()

        // Assert
        val uiState = loginViewModel.uiState.value
        assertFalse(uiState.enableLoginButton)
    }
}