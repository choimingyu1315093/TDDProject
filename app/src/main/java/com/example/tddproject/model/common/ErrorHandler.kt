package com.example.tddproject.model.common

import okio.IOException
import retrofit2.HttpException

class AppException(message: String, cause: Throwable? = null): RuntimeException(message, cause)

object ErrorHandler {
    fun wrap(t: Throwable): AppException {
        val msg = when(t){
            is IOException -> "Network Error"
            is HttpException -> "Server Error ${t.code()}"
            else -> "Unknown Error"
        }
        return AppException(msg, t)
    }
}