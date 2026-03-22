package com.sraccelerator.easyorder.domain.usecase

import com.sraccelerator.easyorder.data.CartRepository
import javax.inject.Inject

interface RemoveProductFromCartUseCase {
    operator fun invoke(productId: Int)
}

internal class RemoveProductFromCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository
) : RemoveProductFromCartUseCase {
    override fun invoke(productId: Int) {
        repository.removeFromCart(productId)
    }
}