package com.sraccelerator.easyorder.presentation.ui.category

import com.sraccelerator.easyorder.data.model.Category

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    data class Success(val categories: List<Category>) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}