package com.aherrera.fakelogin.domain.useCase

import com.aherrera.fakelogin.core.apiResponseHandler.BaseError
import com.aherrera.fakelogin.core.apiResponseHandler.DataState
import com.aherrera.fakelogin.data.model.request.LoginRequest
import com.aherrera.fakelogin.domain.model.UserDetails
import com.aherrera.fakelogin.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend operator fun invoke(
        request: LoginRequest
    ): Flow<DataState<UserDetails>> = flow {
        emit(DataState.Loading)

        loginRepository.login(request).collect { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    val userDetails = UserDetails.mapFromResponse(dataState.data)
                    emit(DataState.Success(userDetails))
                }

                is DataState.Error -> {
                    emit(DataState.Error(dataState.error))
                }

                else -> {
                    DataState.Loading
                }
            }
        }
    }.catch { e ->
        emit(DataState.Error(BaseError(localMessage = e.message ?: "Unknown error")))
    }

}