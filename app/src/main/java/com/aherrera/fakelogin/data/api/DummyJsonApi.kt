package com.aherrera.fakelogin.data.api

import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface DummyJsonApi {

    @POST("auth/login")
    suspend fun login(
        request: LoginRequest
    ): Response<LoginResponse>

}