package com.aherrera.fakelogin.data.dataSource.remote

import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.data.model.response.LoginResponse
import retrofit2.Response

interface LoginRemoteDataSource {
    suspend fun login(request: LoginRequest): Response<LoginResponse>
}