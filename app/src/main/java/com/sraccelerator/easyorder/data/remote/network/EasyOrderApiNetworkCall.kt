package com.sraccelerator.easyorder.data.remote.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

internal class EasyOrderApiNetworkCall<S : Any>(
    private val delegate: Call<S>
) : Call<EasyOrderApiResponse<S>> {

    override fun clone(): Call<EasyOrderApiResponse<S>> =
        EasyOrderApiNetworkCall(delegate.clone())

    override fun execute(): Response<EasyOrderApiResponse<S>> {
        throw UnsupportedOperationException("EasyOrderApiNetworkCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun enqueue(callback: Callback<EasyOrderApiResponse<S>>) {
        delegate.enqueue(EasyOrderApiCallback(this, callback))
    }

    private class EasyOrderApiCallback<S : Any>(
        private val networkCall: EasyOrderApiNetworkCall<S>,
        private val originalCallback: Callback<EasyOrderApiResponse<S>>
    ) : Callback<S> {

        override fun onResponse(call: Call<S>, response: Response<S>) {
            val easyOrderResponse = response.toEasyOrderApiResponse()

            originalCallback.onResponse(
                networkCall,
                Response.success(easyOrderResponse)
            )
        }

        override fun onFailure(call: Call<S>, throwable: Throwable) {
            val apiResponse = EasyOrderApiResponse.Exception(throwable)

            originalCallback.onResponse(
                networkCall,
                Response.success(apiResponse)
            )
        }
    }
}