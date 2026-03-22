package com.sraccelerator.easyorder.presentation.ui.category.list

sealed class CategoryListUiEvent {
    data class OnCategoryListClick(val categoryId: Int, val categoryName: String) : CategoryListUiEvent()
    data object OnCartClick : CategoryListUiEvent()
    object OnRetryClick : CategoryListUiEvent()
}