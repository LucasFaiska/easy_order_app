package com.sraccelerator.easyorder.presentation.ui.category.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sraccelerator.easyorder.R
import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.presentation.component.EasyOrderCategoryCard
import com.sraccelerator.easyorder.presentation.component.EasyOrderError
import com.sraccelerator.easyorder.presentation.component.EasyOrderHeader
import com.sraccelerator.easyorder.presentation.component.EasyOrderLoading
import com.sraccelerator.easyorder.presentation.component.EasyOrderScaffold
import com.sraccelerator.easyorder.presentation.component.EasyOrderTopBar

@Composable
fun CategoryListScreen(
    state: CategoryListUiState,
    onEvent: (CategoryListUiEvent) -> Unit
) {
    val cartCount = if (state is CategoryListUiState.Success) state.cartItemsCount else 0

    EasyOrderScaffold(
        topBar = {
            EasyOrderTopBar(
                titleRes = R.string.easy_order_default_title,
                cartItemsCount = cartCount,
                onCartClick = {
                    onEvent(CategoryListUiEvent.OnCartClick)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CategoryScreenContent(
                state = state,
                onEvent = onEvent
            )
        }
    }
}

@Composable
fun CategoryScreenContent(state: CategoryListUiState, onEvent: (CategoryListUiEvent) -> Unit) {
    when (state) {
        is CategoryListUiState.Loading -> {
            EasyOrderLoading()
        }

        is CategoryListUiState.Success -> {
            CategorySuccessContent(state.categories, onEvent)
        }

        is CategoryListUiState.Error -> {
            EasyOrderError(message = state.message) {
                onEvent(CategoryListUiEvent.OnRetryClick)
            }
        }
    }
}

@Composable
private fun CategorySuccessContent(
    categories: List<Category>,
    onEvent: (CategoryListUiEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            EasyOrderHeader(
                title = stringResource(R.string.categories_title),
                modifier = Modifier.padding(vertical = 16.dp),
            )
        }

        items(
            categories,
            key = { category -> category.id }
        ) { category ->
            EasyOrderCategoryCard(
                category = category,
                onClick = { onEvent(CategoryListUiEvent.OnCategoryListClick(category.id, category.name)) }
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Preview(showBackground = true, name = "Success State")
@Composable
fun EasyOrderCategoriesScreenPreview() {
    val categories = listOf(
        Category(
            id = 21,
            name = "Traditional Pizzas",
            imageUrl = "https://loremflickr.com/320/240/pizza?lock=21",
            displayOrder = 1,
            isActive = true
        ),
        Category(
            id = 22,
            name = "Artisanal Pasta",
            imageUrl = "https://loremflickr.com/320/240/pasta?lock=22",
            displayOrder = 2,
            isActive = true
        )
    )

    CategoryListScreen(
        state = CategoryListUiState.Success(categories),
        onEvent = {}
    )
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun EasyOrderCategoriesLoadingPreview() {
    CategoryListScreen(
        state = CategoryListUiState.Loading,
        onEvent = {}
    )
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun EasyOrderCategoriesErrorPreview() {
    CategoryListScreen(
        state = CategoryListUiState.Error(
            message = "Connection failed. Please check your internet."
        ),
        onEvent = {}
    )
}
