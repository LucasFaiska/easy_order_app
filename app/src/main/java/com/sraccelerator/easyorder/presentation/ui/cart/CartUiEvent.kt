package com.sraccelerator.easyorder.presentation.ui.cart

import com.sraccelerator.easyorder.data.model.Product

sealed class CartUiEvent {
    data class OnIncreaseQuantity(val product: Product) : CartUiEvent()
    data class OnDecreaseQuantity(val productId: Int) : CartUiEvent()
    object OnCheckoutClick : CartUiEvent()
    object OnBackClick : CartUiEvent()
}