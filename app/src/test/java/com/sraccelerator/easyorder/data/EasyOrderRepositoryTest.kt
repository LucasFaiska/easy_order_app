package com.sraccelerator.easyorder.data

import app.cash.turbine.test
import com.sraccelerator.easyorder.CoroutineTestRule
import com.sraccelerator.easyorder.data.remote.RemoteDataSource
import com.sraccelerator.easyorder.data.remote.dto.response.CategoryDTO
import com.sraccelerator.easyorder.data.remote.dto.response.ProductDTO
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class EasyOrderRepositoryTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: EasyOrderRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = EasyOrderRepositoryImpl(remoteDataSource, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `getCategories should emit loading and success when data source returns success`() = runTest {
        val mockDto = CategoryDTO(1, "Burguers", "url", 1, "active")
        coEvery { remoteDataSource.getCategories(any()) } returns EasyOrderApiResponse.Success(listOf(mockDto))

        repository.getCategories(1).test {
            assertTrue(awaitItem() is DataState.Loading)
            val success = awaitItem() as DataState.Success
            assertEquals(1, success.data.size)
            assertEquals("Burguers", success.data[0].name)
            assertTrue(success.data[0].isActive)
            awaitComplete()
        }
    }

    @Test
    fun `getCategories should emit loading and error when data source returns api error`() = runTest {
        coEvery { remoteDataSource.getCategories(any()) } returns EasyOrderApiResponse.Error(404, "Not Found")

        repository.getCategories(1).test {
            assertTrue(awaitItem() is DataState.Loading)
            val error = awaitItem() as DataState.Error
            assertTrue(error.throwable.message?.contains("404") == true)
            awaitComplete()
        }
    }

    @Test
    fun `getCategories should emit loading and error when data source throws exception`() = runTest {
        val exception = IOException("No Internet")
        coEvery { remoteDataSource.getCategories(any()) } returns EasyOrderApiResponse.Exception(exception)

        repository.getCategories(1).test {
            assertTrue(awaitItem() is DataState.Loading)
            val error = awaitItem() as DataState.Error
            assertEquals(exception, error.throwable)
            awaitComplete()
        }
    }

    @Test
    fun `getProductsByCategory should emit loading and success when data source returns success`() = runTest {
        val mockDto = ProductDTO(10, "Cheese", "Desc", 15.0, null, "url", true, true, 4.0, "15 min", listOf("tag"))
        coEvery { remoteDataSource.getProductsByCategory(any(), any()) } returns EasyOrderApiResponse.Success(listOf(mockDto))

        repository.getProductsByCategory(1, 1).test {
            assertTrue(awaitItem() is DataState.Loading)
            val success = awaitItem() as DataState.Success
            val product = success.data.first()
            assertEquals(10, product.id)
            assertTrue(product.isPromoted)
            awaitComplete()
        }
    }

    @Test
    fun `getProductsByCategory should emit loading and error when data source returns server error`() = runTest {
        coEvery { remoteDataSource.getProductsByCategory(any(), any()) } returns EasyOrderApiResponse.Error(500, "Internal Error")

        repository.getProductsByCategory(1, 1).test {
            assertTrue(awaitItem() is DataState.Loading)
            val error = awaitItem() as DataState.Error
            assertTrue(error.throwable.message?.contains("500") == true)
            awaitComplete()
        }
    }

    @Test
    fun `getProductsByCategory should emit loading and success with empty list`() = runTest {
        coEvery { remoteDataSource.getProductsByCategory(any(), any()) } returns EasyOrderApiResponse.Success(emptyList())

        repository.getProductsByCategory(1, 1).test {
            assertTrue(awaitItem() is DataState.Loading)
            val success = awaitItem() as DataState.Success
            assertTrue(success.data.isEmpty())
            awaitComplete()
        }
    }
}