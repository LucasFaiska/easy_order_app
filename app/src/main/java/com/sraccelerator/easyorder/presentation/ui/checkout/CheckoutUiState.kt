package com.sraccelerator.easyorder.presentation.ui.checkout

import com.sraccelerator.easyorder.data.model.CartItem

data class CheckoutUiState(
    val items: List<CartItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val isPickupEnabled: Boolean = false,
    val isCouponEnabled: Boolean = false,
    val supportWhatsapp: String? = null,
    val isLoading: Boolean = false
)
