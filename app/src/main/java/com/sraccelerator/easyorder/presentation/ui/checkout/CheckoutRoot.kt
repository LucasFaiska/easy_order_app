package com.sraccelerator.easyorder.presentation.ui.checkout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CheckoutRoot(
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    CheckoutScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}
