package com.sraccelerator.easyorder.data.remote.network

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class EasyOrderApiNetworkAdapter<S : Any>(
    private val successType: Type
) : CallAdapter<S, Call<EasyOrderApiResponse<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<EasyOrderApiResponse<S>> {
        return EasyOrderApiNetworkCall(call)
    }
}