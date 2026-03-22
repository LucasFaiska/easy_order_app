package com.sraccelerator.easyorder.presentation.ui.product.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sraccelerator.easyorder.data.DataState
import com.sraccelerator.easyorder.data.model.Product
import com.sraccelerator.easyorder.domain.usecase.GetProductsByCategoryUseCase
import com.sraccelerator.easyorder.presentation.navigation.AppRoutes
import com.sraccelerator.easyorder.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductListViewModel @Inject constructor(
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val categoryId = savedStateHandle.toRoute<AppRoutes.ProductList>().categoryId
    private val categoryName = savedStateHandle.toRoute<AppRoutes.ProductList>().categoryName

    private val _uiState = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val state = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun onEvent(event: ProductListUiEvent) {
        when (event) {
            is ProductListUiEvent.OnRetryClick -> loadProducts()
            is ProductListUiEvent.OnAddToCartClick -> {

            }
            is ProductListUiEvent.OnBackClick -> {
                viewModelScope.launch {
                    navigator.navigateBack()
                }
            }
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            getProductsByCategoryUseCase(categoryId).collect { result ->
                handleLoadProductsResult(result)
            }
        }
    }

    private fun handleLoadProductsResult(result: DataState<List<Product>>) {
        when (result) {
            is DataState.Loading -> {
                _uiState.value = ProductListUiState.Loading
            }
            is DataState.Success -> {
                val products = result.data
                _uiState.value = ProductListUiState.Success(categoryName, products)
            }
            is DataState.Error -> {
                _uiState.value = ProductListUiState.Error(
                    result.throwable.message ?: "Failed to load products"
                )
            }
        }
    }
}