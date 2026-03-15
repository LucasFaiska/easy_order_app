package com.sraccelerator.easyorder.data.remote.network

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.Assert.assertEquals
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class EasyOrderApiNetworkCallTest {

    private val delegate = mockk<Call<String>>()
    private val easyOrderCall = EasyOrderApiNetworkCall(delegate)
    private val callback = mockk<Callback<EasyOrderApiResponse<String>>>(relaxed = true)

    @Test
    fun `given 200 response when enqueue then return Success`() {
        val slot = slot<Callback<String>>()
        every { delegate.enqueue(capture(slot)) } answers {
            slot.captured.onResponse(delegate, Response.success("data"))
        }

        easyOrderCall.enqueue(callback)

        val responseSlot = slot<Response<EasyOrderApiResponse<String>>>()
        verify { callback.onResponse(any(), capture(responseSlot)) }
        assertTrue(responseSlot.captured.body() is EasyOrderApiResponse.Success)
    }

    @Test
    fun `given 404 response when enqueue then return Error state with code`() {
        val slot = slot<Callback<String>>()
        every { delegate.enqueue(capture(slot)) } answers {
            slot.captured.onResponse(delegate, Response.error(404, "".toResponseBody(null)))
        }

        easyOrderCall.enqueue(callback)

        val responseSlot = slot<Response<EasyOrderApiResponse<String>>>()
        verify { callback.onResponse(any(), capture(responseSlot)) }

        val body = responseSlot.captured.body() as EasyOrderApiResponse.Error
        assertEquals(404, body.code)
    }

    @Test
    fun `given IOException when enqueue then return Exception state`() {
        val slot = slot<Callback<String>>()
        val throwable = IOException("No Internet")
        every { delegate.enqueue(capture(slot)) } answers {
            slot.captured.onFailure(delegate, throwable)
        }

        easyOrderCall.enqueue(callback)

        val responseSlot = slot<Response<EasyOrderApiResponse<String>>>()
        verify { callback.onResponse(any(), capture(responseSlot)) }

        val body = responseSlot.captured.body() as EasyOrderApiResponse.Exception
        assertEquals(throwable, body.e)
    }
}