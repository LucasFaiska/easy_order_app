package com.sraccelerator.easyorder.data.remote.network

sealed class EasyOrderApiResponse<out T : Any> {
    data class Success<T : Any>(val body: T) : EasyOrderApiResponse<T>()
    data class Error(val code: Int, val message: String) : EasyOrderApiResponse<Nothing>()
    data class Exception(val e: Throwable) : EasyOrderApiResponse<Nothing>()
}