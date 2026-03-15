package com.sraccelerator.easyorder.domain.usecase

import com.sraccelerator.easyorder.data.DataState
import com.sraccelerator.easyorder.data.EasyOrderRepository
import com.sraccelerator.easyorder.data.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCategoriesUseCase {
    operator fun invoke(restaurantId: Int): Flow<DataState<List<Category>>>
}

internal class GetCategoriesUseCaseImpl @Inject constructor(
    private val repository: EasyOrderRepository
) : GetCategoriesUseCase {
    override fun invoke(restaurantId: Int): Flow<DataState<List<Category>>> {
        return repository.getCategories(restaurantId)
    }
}