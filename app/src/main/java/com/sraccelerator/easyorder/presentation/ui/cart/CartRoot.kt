package com.sraccelerator.easyorder.presentation.ui.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CartRoot() {
    val viewModel: CartViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    CartScreen(state, viewModel.toastEvent, viewModel::onEvent)
}