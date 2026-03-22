package com.sraccelerator.easyorder.domain.usecase

import com.sraccelerator.easyorder.data.CartRepository
import com.sraccelerator.easyorder.data.model.CartItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCartUseCase {
    operator fun invoke(): Flow<List<CartItem>>
}

internal class GetCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository
) : GetCartUseCase {
    override fun invoke(): Flow<List<CartItem>> = repository.cartItems
}