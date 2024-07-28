package com.aherrera.fakelogin.data.repository

import com.aherrera.fakelogin.core.apiResponseHandler.BaseError
import com.aherrera.fakelogin.core.apiResponseHandler.DataState
import com.aherrera.fakelogin.data.dataSource.remote.LoginRemoteDataSource
import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.data.model.response.LoginResponse
import com.aherrera.fakelogin.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl(
    private val loginRemoteDs: LoginRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : LoginRepository {

    override suspend fun login(request: LoginRequest): Flow<DataState<LoginResponse>> = flow {
        val response = loginRemoteDs.login(request)

        if (response.isSuccessful.not()) {
            emit(
                DataState.Error(
                    error = BaseError(
                        localMessage = response.message() ?: "Something went wrong",
                        code = response.code()
                    )
                )
            )
            return@flow
        }

        response.body()?.let { safeBody ->
            emit(DataState.Success(data = safeBody))
        } ?: emit(DataState.Error(error = BaseError(localMessage = "Null body!!")))

    }.catch {
        emit(DataState.Error(error = BaseError(localMessage = it.message ?: "Something went wrong")))
    }.flowOn(ioDispatcher)
}