package com.sraccelerator.easyorder.domain.usecase

import com.sraccelerator.easyorder.data.CartRepository
import com.sraccelerator.easyorder.data.model.Product
import javax.inject.Inject

interface AddProductToCartUseCase {
    operator fun invoke(product: Product)
}

internal class AddProductToCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository
) : AddProductToCartUseCase {
    override fun invoke(product: Product) {
        repository.addToCart(product)
    }
}