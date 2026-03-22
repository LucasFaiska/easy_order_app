package com.sraccelerator.easyorder.presentation.ui.product.list

import com.sraccelerator.easyorder.data.model.Product

sealed class ProductListUiEvent {
    data class OnAddToCartClick(val product: Product) : ProductListUiEvent()
    object OnBackClick : ProductListUiEvent()
    object OnRetryClick : ProductListUiEvent()
}