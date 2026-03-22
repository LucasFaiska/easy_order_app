package com.sraccelerator.easyorder.presentation.ui.category.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sraccelerator.easyorder.data.DataState
import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.domain.usecase.GetCategoriesUseCase
import com.sraccelerator.easyorder.presentation.navigation.AppRoutes
import com.sraccelerator.easyorder.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoryListUiState>(CategoryListUiState.Loading)
    val state = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase(1).collect { categoriesState ->
                when (categoriesState) {
                    is DataState.Error -> _uiState.value = CategoryListUiState.Error(categoriesState.throwable.message.orEmpty())
                    DataState.Loading -> _uiState.value = CategoryListUiState.Loading
                    is DataState.Success<*> -> _uiState.value = CategoryListUiState.Success(categoriesState.data as List<Category>)
                }
            }
        }
    }

    fun onEvent(event: CategoryListUiEvent) {
        when (event) {
            is CategoryListUiEvent.OnCategoryListClick -> {
                viewModelScope.launch {
                    navigator.navigateTo(AppRoutes.ProductList(event.categoryId, event.categoryName))
                }
            }
            CategoryListUiEvent.OnRetryClick -> loadCategories()
        }
    }
}