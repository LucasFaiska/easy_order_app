package com.sraccelerator.easyorder.data.remote.api

import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiNetworkAdapterFactory
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EasyOrderApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: EasyOrderApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EasyOrderApiNetworkAdapterFactory())
            .build()
            .create(EasyOrderApiService::class.java)
    }

    @After
    fun teardown() = mockWebServer.shutdown()

    @Test
    fun `given categories_success json when getCategories then return success with parsed list`() = runBlocking {
        val json = """[{"id": 1, "name": "Burguer", "status": "active"}]"""
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

        val response = apiService.getCategories(1)

        assertTrue(response is EasyOrderApiResponse.Success)
        val success = response as EasyOrderApiResponse.Success
        assertEquals(1, success.body.size)
        assertEquals("Burguer", success.body[0].name)
    }
}