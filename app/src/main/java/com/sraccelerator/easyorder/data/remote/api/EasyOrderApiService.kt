package com.sraccelerator.easyorder.data.remote.api

import com.sraccelerator.easyorder.data.remote.dto.response.CategoryDTO
import com.sraccelerator.easyorder.data.remote.dto.response.ProductDTO
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EasyOrderApiService {

    @GET("api/v1/{restaurantId}/categories/index.json")
    suspend fun getCategories(
        @Path("restaurantId") restaurantId: Int
    ): EasyOrderApiResponse<List<CategoryDTO>>

    @GET("api/v1/{categoryId}/products/index.json")
    suspend fun getProductsByCategory(
        @Path("categoryId") categoryId: Int
    ): EasyOrderApiResponse<List<ProductDTO>>
}