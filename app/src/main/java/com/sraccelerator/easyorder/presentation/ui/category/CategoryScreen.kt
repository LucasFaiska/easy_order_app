package com.sraccelerator.easyorder.presentation.ui.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.sraccelerator.easyorder.presentation.theme.*

@Composable
fun EasyOrderCategoriesScreen(
    state: CategoryUiState,
    onEvent: (CategoryUiEvent) -> Unit
) {
    EasyOrderScaffold(
        topBar = {
            EasyOrderTopBar(titleRes = R.string.easy_order_default_title)
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
fun CategoryScreenContent(state: CategoryUiState, onEvent: (CategoryUiEvent) -> Unit) {
    when (state) {
        is CategoryUiState.Loading -> {
            EasyOrderLoading()
        }

        is CategoryUiState.Success -> {
            CategorySuccessContent(state.categories) {

            }
        }

        is CategoryUiState.Error -> {
            EasyOrderError(message = state.message) {
                onEvent(CategoryUiEvent.OnRetryClick)
            }
        }
    }
}

@Composable
private fun CategorySuccessContent(
    categories: List<Category>,
    onEvent: (CategoryUiEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            EasyOrderHeader(
                titleRes = R.string.categories_title,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        items(
            categories,
            key = { category -> category.id }
        ) { category ->
            EasyOrderCategoryCard(
                category = category,
                onClick = { onEvent(CategoryUiEvent.OnCategoryClick(category.id)) }
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

    EasyOrderCategoriesScreen(
        state = CategoryUiState.Success(categories),
        onEvent = {}
    )
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun EasyOrderCategoriesLoadingPreview() {
    EasyOrderCategoriesScreen(
        state = CategoryUiState.Loading,
        onEvent = {}
    )
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun EasyOrderCategoriesErrorPreview() {
    EasyOrderCategoriesScreen(
        state = CategoryUiState.Error(
            message = "Connection failed. Please check your internet."
        ),
        onEvent = {}
    )
}
