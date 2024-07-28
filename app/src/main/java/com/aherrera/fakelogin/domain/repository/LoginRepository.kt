package com.aherrera.fakelogin.domain.repository

import com.aherrera.fakelogin.core.apiResponseHandler.DataState
import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.data.model.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(
        request: LoginRequest
    ): Flow<DataState<LoginResponse>>
}