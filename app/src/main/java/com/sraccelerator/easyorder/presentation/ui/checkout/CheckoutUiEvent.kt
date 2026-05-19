package com.sraccelerator.easyorder.presentation.ui.checkout

sealed interface CheckoutUiEvent {
    data object OnBackClick : CheckoutUiEvent
    data object OnPlaceOrderClick : CheckoutUiEvent
    data object OnSupportClick : CheckoutUiEvent
}
