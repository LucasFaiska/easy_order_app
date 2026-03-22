package com.sraccelerator.easyorder.presentation.ui.product.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProductListRoot() {
    val viewModel: ProductListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductListScreen(state, viewModel::onEvent)
}