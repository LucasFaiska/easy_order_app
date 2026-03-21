package com.sraccelerator.easyorder.presentation.ui.category

sealed class CategoryUiEvent {
    data class OnCategoryClick(val categoryId: Int) : CategoryUiEvent()
    object OnRetryClick : CategoryUiEvent()
}