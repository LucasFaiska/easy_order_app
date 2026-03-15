package com.sraccelerator.easyorder.data.remote.network

import retrofit2.Response

internal fun <S : Any> Response<S>.toEasyOrderApiResponse(): EasyOrderApiResponse<S> {
    if (this.isSuccessful) {
        val body = this.body()
        return if (body != null) {
            EasyOrderApiResponse.Success(body)
        } else {
            EasyOrderApiResponse.Error(this.code(), "Empty response body")
        }
    }

    return EasyOrderApiResponse.Error(this.code(), this.message())
}