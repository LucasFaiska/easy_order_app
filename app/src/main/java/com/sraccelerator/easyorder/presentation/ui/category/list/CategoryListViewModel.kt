package com.sraccelerator.easyorder.presentation.ui.category.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sraccelerator.easyorder.core.config.AppConfig
import com.sraccelerator.easyorder.data.DataState
import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.domain.usecase.GetCartItemsCountUseCase
import com.sraccelerator.easyorder.domain.usecase.GetCategoriesUseCase
import com.sraccelerator.easyorder.presentation.navigation.AppRoutes.Cart
import com.sraccelerator.easyorder.presentation.navigation.AppRoutes.ProductList
import com.sraccelerator.easyorder.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCartItemsCountUseCase: GetCartItemsCountUseCase,
    private val navigator: Navigator,
    private val appConfig: AppConfig
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoryListUiState>(CategoryListUiState.Loading)
    val state = _uiState.asStateFlow()

    init {
        observeScreenData()
    }

    private fun observeScreenData() {
        viewModelScope.launch {
            combine(
                getCategoriesUseCase(appConfig.restaurantId),
                getCartItemsCountUseCase()
            ) { categoriesState, cartCount ->
                mapToUiState(categoriesState, cartCount)
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    private fun mapToUiState(
        categoriesState: DataState<List<Category>>,
        cartCount: Int
    ): CategoryListUiState {
        return when (categoriesState) {
            is DataState.Loading -> CategoryListUiState.Loading

            is DataState.Error -> CategoryListUiState.Error(
                categoriesState.throwable.message.orEmpty()
            )

            is DataState.Success -> CategoryListUiState.Success(
                categories = categoriesState.data,
                cartItemsCount = cartCount
            )
        }
    }

    fun onEvent(event: CategoryListUiEvent) {
        when (event) {
            is CategoryListUiEvent.OnCategoryListClick -> {
                viewModelScope.launch {
                    navigator.navigateTo(ProductList(event.categoryId, event.categoryName))
                }
            }

            CategoryListUiEvent.OnRetryClick -> observeScreenData()

            CategoryListUiEvent.OnCartClick -> viewModelScope.launch {
                navigator.navigateTo(Cart)
            }
        }
    }
}
