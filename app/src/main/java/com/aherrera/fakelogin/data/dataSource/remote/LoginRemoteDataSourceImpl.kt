package com.aherrera.fakelogin.data.dataSource.remote

import com.aherrera.fakelogin.data.api.DummyJsonApi
import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.data.model.response.LoginResponse
import retrofit2.Response

class LoginRemoteDataSourceImpl(
    private val api: DummyJsonApi
) : LoginRemoteDataSource {

    override suspend fun login(
        request: LoginRequest
    ): Response<LoginResponse> = api.login(request = request)
}