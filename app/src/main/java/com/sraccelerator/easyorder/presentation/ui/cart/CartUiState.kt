package com.sraccelerator.easyorder.presentation.ui.cart

import com.sraccelerator.easyorder.data.model.CartItem

sealed class CartUiState {
    object Loading : CartUiState()

    object Empty : CartUiState()

    data class Success(
        val items: List<CartItem>,
        val totalPrice: Double,
    ) : CartUiState()

    data class Error(val message: String) : CartUiState()
}