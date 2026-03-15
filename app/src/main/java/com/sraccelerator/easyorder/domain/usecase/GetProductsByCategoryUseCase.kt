package com.sraccelerator.easyorder.domain.usecase

import com.sraccelerator.easyorder.data.DataState
import com.sraccelerator.easyorder.data.EasyOrderRepository
import com.sraccelerator.easyorder.data.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetProductsByCategoryUseCase {
    operator fun invoke(restaurantId: Int, categoryId: Int): Flow<DataState<List<Product>>>
}

internal class GetProductsByCategoryUseCaseImpl @Inject constructor(
    private val repository: EasyOrderRepository
) : GetProductsByCategoryUseCase {
    override fun invoke(restaurantId: Int, categoryId: Int): Flow<DataState<List<Product>>> {
        return repository.getProductsByCategory(restaurantId, categoryId)
    }
}