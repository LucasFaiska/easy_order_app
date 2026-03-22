package com.sraccelerator.easyorder.presentation.ui.product.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sraccelerator.easyorder.R
import com.sraccelerator.easyorder.data.model.Product
import com.sraccelerator.easyorder.presentation.component.EasyOrderBackButton
import com.sraccelerator.easyorder.presentation.component.EasyOrderError
import com.sraccelerator.easyorder.presentation.component.EasyOrderHeader
import com.sraccelerator.easyorder.presentation.component.EasyOrderLoading
import com.sraccelerator.easyorder.presentation.component.EasyOrderProductCard
import com.sraccelerator.easyorder.presentation.component.EasyOrderScaffold
import com.sraccelerator.easyorder.presentation.component.EasyOrderTopBar
import com.sraccelerator.easyorder.presentation.theme.EasyOrderTheme

@Composable
fun ProductListScreen(
    state: ProductListUiState,
    onEvent: (ProductListUiEvent) -> Unit
) {
    val cartCount = if (state is ProductListUiState.Success) state.cartItemsCount else 0

    EasyOrderScaffold(
        topBar = {
            EasyOrderTopBar(
                titleRes = R.string.product_list_title,
                navigationIcon = {
                    EasyOrderBackButton(onClick = { onEvent(ProductListUiEvent.OnBackClick) })
                },
                cartItemsCount = cartCount,
                onCartClick = {
                    onEvent(ProductListUiEvent.OnCartClick)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            ProductScreenContent(state, onEvent)
        }
    }
}

@Composable
private fun ProductScreenContent(
    state: ProductListUiState,
    onEvent: (ProductListUiEvent) -> Unit
) {
    when (state) {
        is ProductListUiState.Loading -> EasyOrderLoading()
        is ProductListUiState.Error -> EasyOrderError(message = state.message) {
            onEvent(ProductListUiEvent.OnRetryClick)
        }

        is ProductListUiState.Success -> ProductSuccessContent(state, onEvent)
    }
}

@Composable
private fun ProductSuccessContent(
    state: ProductListUiState.Success,
    onEvent: (ProductListUiEvent) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            EasyOrderHeader(
                title = state.categoryName,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        items(state.products, key = { it.id }) { product ->
            EasyOrderProductCard(
                product = product,
                onAddClick = { onEvent(ProductListUiEvent.OnAddToCartClick(product)) }
            )
        }
    }
}

@Preview(showBackground = true, name = "Product List - Loading")
@Composable
fun ProductListScreenLoadingPreview() {
    EasyOrderTheme {
        ProductListScreen(
            state = ProductListUiState.Loading,
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, name = "Product List - Success")
@Composable
fun ProductListScreenSuccessPreview() {
    EasyOrderTheme {
        ProductListScreen(
            state = ProductListUiState.Success(
                categoryName = "Signature Burgers",
                products = listOf(
                    Product(
                        id = 101,
                        name = "Classic Neon Cheeseburger",
                        description = "Single Prime beef, cheddar, Neon sauce, bun.",
                        price = 14.00,
                        originalPrice = null,
                        imageUrl = "https://images.unsplash.com/photo-1572449043416-55f4685c9bb7?w=300&q=80",
                        isAvailable = true,
                        isPromoted = false,
                        rating = 4.5,
                        preparationTime = "15-20",
                        tags = listOf("Classic", "Cheddar")
                    ),
                    Product(
                        id = 102,
                        name = "Velvet Heat Chicken",
                        description = "Nashville hot chicken, honey butter bun, slaw.",
                        price = 12.50,
                        originalPrice = 16.00,
                        imageUrl = "https://images.unsplash.com/photo-1594179047519-f347310d3322?w=300&q=80",
                        isAvailable = true,
                        isPromoted = true,
                        rating = 4.8,
                        preparationTime = "10-15",
                        tags = listOf("Spicy", "Chicken")
                    ),
                    Product(
                        id = 103,
                        name = "Double Truffle Canvas",
                        description = "Wagyu, black truffle aioli, gold leaf brioche.",
                        price = 24.50,
                        originalPrice = null,
                        imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=300&q=80",
                        isAvailable = true,
                        isPromoted = false,
                        rating = 5.0,
                        preparationTime = "20-25",
                        tags = listOf("Gourmet", "Truffle")
                    )
                )
            ),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, name = "Product List - Error")
@Composable
fun ProductListScreenErrorPreview() {
    EasyOrderTheme {
        ProductListScreen(
            state = ProductListUiState.Error(
                message = "Failed to load products. Please try again."
            ),
            onEvent = {}
        )
    }
}