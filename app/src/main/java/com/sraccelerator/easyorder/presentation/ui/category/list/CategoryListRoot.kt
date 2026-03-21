package com.sraccelerator.easyorder.presentation.ui.category.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CategoryListRoot() {
    val viewModel: CategoryListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    CategoryListScreen(state, viewModel::onEvent)
}