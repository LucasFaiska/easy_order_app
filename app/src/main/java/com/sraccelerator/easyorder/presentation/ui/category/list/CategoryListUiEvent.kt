package com.sraccelerator.easyorder.presentation.ui.category.list

sealed class CategoryListUiEvent {
    data class OnCategoryListClick(val categoryId: Int) : CategoryListUiEvent()
    object OnRetryClick : CategoryListUiEvent()
}