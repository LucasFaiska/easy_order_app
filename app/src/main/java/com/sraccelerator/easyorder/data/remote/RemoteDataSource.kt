package com.sraccelerator.easyorder.data.remote

import com.sraccelerator.easyorder.data.remote.api.EasyOrderApiService
import com.sraccelerator.easyorder.data.remote.dto.response.CategoryDTO
import com.sraccelerator.easyorder.data.remote.dto.response.FeatureFlagsResponse
import com.sraccelerator.easyorder.data.remote.dto.response.ProductDTO
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiResponse
import javax.inject.Inject

internal interface RemoteDataSource {
    suspend fun getCategories(restaurantId: Int): EasyOrderApiResponse<List<CategoryDTO>>

    suspend fun getProductsByCategory(
        categoryId: Int
    ): EasyOrderApiResponse<List<ProductDTO>>

    suspend fun getFeatureFlags(): EasyOrderApiResponse<FeatureFlagsResponse>
}

internal class RemoteDataSourceImpl @Inject constructor(
    private val apiService: EasyOrderApiService
) : RemoteDataSource {

    override suspend fun getCategories(restaurantId: Int): EasyOrderApiResponse<List<CategoryDTO>> {
        return apiService.getCategories(restaurantId)
    }

    override suspend fun getProductsByCategory(
        categoryId: Int
    ): EasyOrderApiResponse<List<ProductDTO>> {
        return apiService.getProductsByCategory(categoryId)
    }

    override suspend fun getFeatureFlags(): EasyOrderApiResponse<FeatureFlagsResponse> {
        return apiService.getFeatureFlags()
    }
}
