package com.aherrera.fakelogin.core.apiResponseHandler

import androidx.annotation.StringRes
import com.aherrera.fakelogin.R

data class BaseError(
    var localMessage: String = "",
    @StringRes var message: Int = ErrorType.UNKNOWN.message,
    var code: Int = ErrorType.UNKNOWN.codeValue,
    var exception: Exception? = null
)

internal enum class ErrorType(val codeValue: Int, @StringRes val message: Int) {

    /**
     * 4XX Errors
     */
    BAD_REQUEST(codeValue = 400, message = R.string.bad_request),
    UNAUTHORIZED(codeValue = 401, message = R.string.unauthorized),
    FORBIDDEN(codeValue = 403, message = R.string.forbidden),
    NOT_FOUND(codeValue = 404, message = R.string.not_found),
    NOT_ACCEPTABLE(codeValue = 406, message = R.string.general_error_message),
    REQUEST_TIME_OUT(codeValue = 408, message = R.string.timeout),
    METHOD_NOT_ALLOWED(codeValue = 409, message = R.string.method_not_allowed),

    /**
     * 5XX Errors
     */
    INTERNAL_SERVER_ERROR(codeValue = 500, message = R.string.internal_server_error),
    NOT_IMPLEMENTED(codeValue = 501, message = R.string.general_error_message),
    BAD_GATEWAY(codeValue = 502, message = R.string.bad_gateway),
    SERVICE_UNAVAILABLE(codeValue = 503, message = R.string.service_unavailable),
    GATEWAY_TIMEOUT(codeValue = 504, message = R.string.timeout),

    /**
     * I/O Exceptions
     */
    IO_TIMEOUT(codeValue = -2000, message = R.string.timeout),
    CONNECTION_FAILED(codeValue = -2001, message = R.string.timeout),
    NO_INFORMATION_RETRIEVED(codeValue = -2002, message = R.string.timeout),
    INCOMPLETE_INFORMATION(codeValue = -2003, message = R.string.timeout),

    /**
     * Unknown exceptions
     */
    UNKNOWN(codeValue = -1000, message = R.string.general_error_message);

    companion object {
        private val errorMap = values().associateBy(ErrorType::codeValue)
        fun getErrorMessage(errorCode: Int): Int = errorMap[errorCode]?.message
            ?: UNKNOWN.message

        fun getErrorType(errorCode: Int): ErrorType = errorMap[errorCode] ?: UNKNOWN
    }
}
