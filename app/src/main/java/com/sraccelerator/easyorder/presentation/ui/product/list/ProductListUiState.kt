package com.sraccelerator.easyorder.presentation.ui.product.list

import com.sraccelerator.easyorder.data.model.Product

sealed class ProductListUiState {
    object Loading : ProductListUiState()

    data class Success(
        val categoryName: String,
        val products: List<Product>
    ) : ProductListUiState()

    data class Error(val message: String) : ProductListUiState()
}