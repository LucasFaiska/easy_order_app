package com.sraccelerator.easyorder.presentation.ui.cart

import com.sraccelerator.easyorder.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sraccelerator.easyorder.domain.usecase.AddProductToCartUseCase
import com.sraccelerator.easyorder.domain.usecase.ClearCartUseCase
import com.sraccelerator.easyorder.domain.usecase.GetCartUseCase
import com.sraccelerator.easyorder.domain.usecase.RemoveProductFromCartUseCase
import com.sraccelerator.easyorder.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow<CartUiState>(CartUiState.Loading)
    val state = _uiState.asStateFlow()

    private val _toastEvent = MutableSharedFlow<Int>()
    val toastEvent = _toastEvent.asSharedFlow()

    private var observeJob: Job? = null

    init {
        loadCart()
    }

    private fun loadCart() {
        observeJob?.cancel()
        observeJob = viewModelScope.launch {
            getCartUseCase().collect { items ->
                _uiState.value = if (items.isEmpty()) {
                    CartUiState.Empty
                } else {
                    CartUiState.Success(
                        items = items,
                        totalPrice = items.sumOf { it.product.price * it.quantity }
                    )
                }
            }
        }
    }

    fun onEvent(event: CartUiEvent) {
        when (event) {
            is CartUiEvent.OnIncreaseQuantity -> addProductToCartUseCase(event.product)
            is CartUiEvent.OnDecreaseQuantity -> removeProductFromCartUseCase(event.productId)
            CartUiEvent.OnBackClick -> viewModelScope.launch { navigator.navigateBack() }
            CartUiEvent.OnCheckoutClick -> performCheckout()
            CartUiEvent.OnRetry -> loadCart()
        }
    }

    private fun performCheckout() {
        viewModelScope.launch {
            _toastEvent.emit(R.string.toast_order_placed)
            clearCartUseCase()
            navigator.navigateBack()
        }
    }
}