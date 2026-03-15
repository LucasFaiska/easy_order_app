package com.sraccelerator.easyorder.data.remote

import com.sraccelerator.easyorder.data.remote.api.EasyOrderApiService
import com.sraccelerator.easyorder.data.remote.dto.response.CategoryDTO
import com.sraccelerator.easyorder.data.remote.dto.response.ProductDTO
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class EasyOrderRemoteDataSourceTest {

    @MockK
    private lateinit var apiService: EasyOrderApiService
    private lateinit var dataSource: RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        dataSource = RemoteDataSourceImpl(apiService)
    }

    @Test
    fun `getCategories should return success response from api service`() = runTest {
        val expected = EasyOrderApiResponse.Success(listOf(CategoryDTO(1, "Burgers", "url", 1, "active")))
        coEvery { apiService.getCategories(1) } returns expected

        val actual = dataSource.getCategories(1)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { apiService.getCategories(1) }
    }

    @Test
    fun `getCategories should return error response from api service`() = runTest {
        val expected = EasyOrderApiResponse.Error(404, "Not Found")
        coEvery { apiService.getCategories(1) } returns expected

        val actual = dataSource.getCategories(1)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { apiService.getCategories(1) }
    }

    @Test
    fun `getCategories should return exception response from api service`() = runTest {
        val expected = EasyOrderApiResponse.Exception(IOException("No Internet"))
        coEvery { apiService.getCategories(1) } returns expected

        val actual = dataSource.getCategories(1)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { apiService.getCategories(1) }
    }

    @Test
    fun `getProductsByCategory should return success response from api service`() = runTest {
        val expected = EasyOrderApiResponse.Success(
            listOf(
                ProductDTO(
                    1,
                    "X-Bacon",
                    "Desc",
                    20.0,
                    null,
                    "url",
                    true,
                    false,
                    4.5,
                    "15 min",
                    null
                )
            )
        )
        coEvery { apiService.getProductsByCategory(1, 10) } returns expected

        val actual = dataSource.getProductsByCategory(1, 10)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { apiService.getProductsByCategory(1, 10) }
    }

    @Test
    fun `getProductsByCategory should return error response from api service`() = runTest {
        val expected = EasyOrderApiResponse.Error(500, "Server Error")
        coEvery { apiService.getProductsByCategory(1, 10) } returns expected

        val actual = dataSource.getProductsByCategory(1, 10)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { apiService.getProductsByCategory(1, 10) }
    }

    @Test
    fun `getProductsByCategory should return exception response from api service`() = runTest {
        val expected = EasyOrderApiResponse.Exception(IOException("Timeout"))
        coEvery { apiService.getProductsByCategory(1, 10) } returns expected

        val actual = dataSource.getProductsByCategory(1, 10)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { apiService.getProductsByCategory(1, 10) }
    }
}