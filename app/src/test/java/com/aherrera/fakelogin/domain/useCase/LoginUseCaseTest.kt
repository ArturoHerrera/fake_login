package com.aherrera.fakelogin.domain.useCase

import com.aherrera.fakelogin.core.apiResponseHandler.BaseError
import com.aherrera.fakelogin.core.apiResponseHandler.DataState
import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.data.model.response.LoginResponse
import com.aherrera.fakelogin.domain.model.UserDetails
import com.aherrera.fakelogin.domain.repository.LoginRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        loginRepository = mock(LoginRepository::class.java)
        loginUseCase = LoginUseCase(loginRepository)
    }

    @Test
    fun `test successful login`() = runTest {
        // Arrange
        val request = LoginRequest(username = "emilys", password = "emilyspass")
        val response = LoginResponse(firstName = "Emily", lastName = "Johnson", image = "https://dummyjson.com/icon/emilys/128")
        val flow: Flow<DataState<LoginResponse>> = flow {
            emit(DataState.Success(response))
        }
        `when`(loginRepository.login(request)).thenReturn(flow)

        // Act
        val result = loginUseCase.invoke(request)

        // Assert
        val dataStates = mutableListOf<DataState<UserDetails>>()
        result.collect { dataState ->
            dataStates.add(dataState)
        }

        assertTrue(dataStates.isNotEmpty())
        assertTrue(dataStates[0] is DataState.Loading)
        assertTrue(dataStates[1] is DataState.Success)
        val successState = dataStates[1] as DataState.Success
        assertEquals("Emily Johnson", successState.data.fullName)
    }

    @Test
    fun `test login error`() = runTest {
        // Arrange
        val request = LoginRequest(username = "testuser", password = "testpass")
        val error = BaseError(localMessage = "Error occurred")
        val flow: Flow<DataState<LoginResponse>> = flow {
            emit(DataState.Error(error))
        }
        `when`(loginRepository.login(request)).thenReturn(flow)

        // Act
        val result = loginUseCase.invoke(request)

        // Assert
        result.collect { dataState ->
            when (dataState) {
                is DataState.Loading -> {}

                is DataState.Error -> {
                    assertEquals("Error occurred", dataState.error.localMessage)
                }
                else -> fail("Expected DataState.Error")
            }
        }
    }

    @Test
    fun `test login with exception`() = runTest {
        // Arrange
        val request = LoginRequest(username = "testuser", password = "testpass")
        val exception = RuntimeException("Network error")
        val flow: Flow<DataState<LoginResponse>> = flow {
            throw exception
        }
        `when`(loginRepository.login(request)).thenReturn(flow)

        // Act
        val result = loginUseCase.invoke(request)

        // Assert
        result.collect { dataState ->
            when (dataState) {
                is DataState.Loading -> {}

                is DataState.Error -> {
                    assertEquals("Network error", dataState.error.localMessage)
                }
                else -> fail("Expected DataState.Error")
            }
        }
    }
}