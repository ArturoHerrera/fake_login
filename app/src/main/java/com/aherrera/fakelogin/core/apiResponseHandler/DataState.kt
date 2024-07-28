package com.aherrera.fakelogin.core.apiResponseHandler

sealed class DataState<out R> {
    object Loading : DataState<Nothing>()
    data class Success<out T : Any>(val data: T) : DataState<T>()
    data class Error(val error: BaseError = BaseError()) : DataState<Nothing>()
    object Idle : DataState<Nothing>()

    val value: R? get() = if(this is Success) data else null
}