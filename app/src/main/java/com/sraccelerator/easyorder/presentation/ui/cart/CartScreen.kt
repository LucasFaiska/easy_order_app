package com.sraccelerator.easyorder.presentation.ui.cart

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sraccelerator.easyorder.R
import com.sraccelerator.easyorder.core.config.MockAppConfig
import com.sraccelerator.easyorder.data.model.CartItem
import com.sraccelerator.easyorder.data.model.Product
import com.sraccelerator.easyorder.presentation.component.EasyOrderBackButton
import com.sraccelerator.easyorder.presentation.component.EasyOrderCartItemCard
import com.sraccelerator.easyorder.presentation.component.EasyOrderError
import com.sraccelerator.easyorder.presentation.component.EasyOrderHeader
import com.sraccelerator.easyorder.presentation.component.EasyOrderLoading
import com.sraccelerator.easyorder.presentation.component.EasyOrderScaffold
import com.sraccelerator.easyorder.presentation.component.EasyOrderTopBar
import com.sraccelerator.easyorder.presentation.theme.EasyOrderTheme
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun CartScreen(
    state: CartUiState,
    toastEvent: SharedFlow<Int>? = null,
    onEvent: (CartUiEvent) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        toastEvent?.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    EasyOrderScaffold(
        topBar = {
            EasyOrderTopBar(
                titleRes = R.string.cart_title, cartItemsCount = 0, navigationIcon = {
                    EasyOrderBackButton(onClick = { onEvent(CartUiEvent.OnBackClick) })
                })
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (state) {
                is CartUiState.Loading -> EasyOrderLoading()
                is CartUiState.Empty -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.cart_empty_message),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                is CartUiState.Success -> CartSuccessContent(state, onEvent)
                is CartUiState.Error -> EasyOrderError(message = state.message) {
                    onEvent(CartUiEvent.OnRetry)
                }
            }
        }
    }
}

@Composable
private fun CartSuccessContent(
    state: CartUiState.Success, onEvent: (CartUiEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                EasyOrderHeader(
                    title = stringResource(R.string.cart_title), modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            items(state.items, key = { it.product.id }) { item ->
                EasyOrderCartItemCard(
                    cartItem = item,
                    onIncrease = { onEvent(CartUiEvent.OnIncreaseQuantity(item.product)) },
                    onDecrease = { onEvent(CartUiEvent.OnDecreaseQuantity(item.product.id)) })
            }
        }
        CartBottomSection(state.totalPrice) {
            onEvent(CartUiEvent.OnCheckoutClick)
        }
    }
}

@Composable
private fun CartBottomSection(
    totalPrice: Double,
    onCheckoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.cart_total_label),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "${stringResource(R.string.product_currency_symbol)} $totalPrice",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Button(
            onClick = onCheckoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = stringResource(R.string.cart_btn_checkout), fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, name = "Success State")
@Composable
private fun CartScreenSuccessPreview() {
    EasyOrderTheme(config = MockAppConfig) {
        androidx.compose.material3.Surface {
            val mockProduct = Product(
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
            )

            val mockItems = listOf(
                CartItem(product = mockProduct, quantity = 2),
                CartItem(product = mockProduct.copy(id = 2, name = "Fries XL", price = 12.0), quantity = 1)
            )

            CartScreen(
                state = CartUiState.Success(
                    items = mockItems,
                    totalPrice = 62.0,
                ),
                onEvent = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Empty State")
@Composable
private fun CartScreenEmptyPreview() {
    EasyOrderTheme(config = MockAppConfig) {
        androidx.compose.material3.Surface {
            CartScreen(
                state = CartUiState.Empty, onEvent = {})
        }
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
private fun CartScreenLoadingPreview() {
    EasyOrderTheme(config = MockAppConfig) {
        androidx.compose.material3.Surface {
            CartScreen(
                state = CartUiState.Loading, onEvent = {})
        }
    }
}

@Preview(showBackground = true, name = "Error State")
@Composable
private fun CartScreenErrorPreview() {
    EasyOrderTheme(config = MockAppConfig) {
        androidx.compose.material3.Surface {
            CartScreen(
                state = CartUiState.Error("Connection timeout. Please try again."), onEvent = {})
        }
    }
}
