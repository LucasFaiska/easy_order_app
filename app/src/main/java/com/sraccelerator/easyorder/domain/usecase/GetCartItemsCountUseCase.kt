package com.sraccelerator.easyorder.domain.usecase

import com.sraccelerator.easyorder.data.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCartItemsCountUseCase {
    operator fun invoke(): Flow<Int>
}

internal class GetCartItemsCountUseCaseImpl @Inject constructor(
    private val repository: CartRepository
) : GetCartItemsCountUseCase {
    override fun invoke(): Flow<Int> = repository.totalItemsCount
}