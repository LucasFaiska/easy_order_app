package com.sraccelerator.easyorder.presentation.ui.category.list

import com.sraccelerator.easyorder.data.model.Category

sealed class CategoryListUiState {
    object Loading : CategoryListUiState()
    data class Success(
        val categories: List<Category>,
        val cartItemsCount: Int = 0
    ) : CategoryListUiState()
    data class Error(val message: String) : CategoryListUiState()
}