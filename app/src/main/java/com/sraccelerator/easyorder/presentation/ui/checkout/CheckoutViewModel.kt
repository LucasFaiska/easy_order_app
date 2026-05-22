package com.sraccelerator.easyorder.presentation.ui.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sraccelerator.easyorder.core.config.AppConfig
import com.sraccelerator.easyorder.core.featureflag.FeatureFlagManager
import com.sraccelerator.easyorder.core.featureflag.FeatureFlagProvider
import com.sraccelerator.easyorder.core.featureflag.FeatureKey
import com.sraccelerator.easyorder.data.FeatureFlagRepository
import com.sraccelerator.easyorder.domain.usecase.GetCartUseCase
import com.sraccelerator.easyorder.presentation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val featureFlagProvider: FeatureFlagProvider,
    private val featureFlagManager: FeatureFlagManager,
    private val getCartUseCase: GetCartUseCase,
    private val navigator: Navigator,
    private val appConfig: AppConfig
) : ViewModel() {

    private val _state = MutableStateFlow(CheckoutUiState(isLoading = true))
    val state = _state.asStateFlow()

    init {
        loadData(appConfig.restaurantId)
    }

    private fun loadData(restaurantId: Int) {
        viewModelScope.launch {
            // Buscamos as flags primeiro para garantir que o estado inicial esteja correto
            featureFlagManager.setup()

            getCartUseCase().collect { items ->
                _state.update {
                    it.copy(
                        items = items,
                        totalPrice = items.sumOf { item -> item.product.price * item.quantity },
                        isPickupEnabled = featureFlagProvider.isEnabled(FeatureKey.CHECKOUT_PICKUP),
                        isCouponEnabled = featureFlagProvider.isEnabled(FeatureKey.CHECKOUT_COUPON),
                        supportWhatsapp = featureFlagProvider.getStringValue(FeatureKey.SUPPORT_CHANNEL),
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onEvent(event: CheckoutUiEvent) {
        when (event) {
            CheckoutUiEvent.OnBackClick -> {
                viewModelScope.launch { navigator.navigateBack() }
            }
            CheckoutUiEvent.OnPlaceOrderClick -> {
                // Lógica de finalização seria aqui
            }
            CheckoutUiEvent.OnSupportClick -> {
                // Lógica para abrir whatsapp seria disparada por SideEffect na View
                // ou via navigator se houver suporte para URL externa
            }
        }
    }
}
