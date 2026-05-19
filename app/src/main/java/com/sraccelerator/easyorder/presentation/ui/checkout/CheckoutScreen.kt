package com.sraccelerator.easyorder.presentation.ui.checkout

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sraccelerator.easyorder.R
import com.sraccelerator.easyorder.presentation.component.EasyOrderBackButton
import com.sraccelerator.easyorder.presentation.component.EasyOrderHeader
import com.sraccelerator.easyorder.presentation.component.EasyOrderLoading
import com.sraccelerator.easyorder.presentation.component.EasyOrderScaffold
import com.sraccelerator.easyorder.presentation.component.EasyOrderTopBar

@Composable
fun CheckoutScreen(
    state: CheckoutUiState,
    onEvent: (CheckoutUiEvent) -> Unit
) {
    val context = LocalContext.current

    EasyOrderScaffold(
        topBar = {
            EasyOrderTopBar(
                titleRes = R.string.checkout_title,
                cartItemsCount = 0,
                navigationIcon = {
                    EasyOrderBackButton(onClick = { onEvent(CheckoutUiEvent.OnBackClick) })
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            EasyOrderLoading()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { EasyOrderHeader(title = "Finalizar Pedido") }

                // 1. Resumo dos Itens
                items(state.items) { item ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("${item.quantity}x ${item.product.name}")
                        Text("R$ ${item.product.price * item.quantity}")
                    }
                }

                item { HorizontalDivider() }

                // 2. Opções de Entrega (Feature Flag: checkout_pickup_at_store)
                item {
                    Text("Método de Entrega", fontWeight = FontWeight.Bold)
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = true, onClick = {})
                            Text("Entrega Padrão")
                        }
                        if (state.isPickupEnabled) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(selected = false, onClick = {})
                                Text("Retirar na Loja (Grátis)")
                            }
                        }
                    }
                }

                // 3. Cupom de Desconto (Feature Flag: checkout_coupon_input)
                if (state.isCouponEnabled) {
                    item {
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            label = { Text("Cupom de Desconto") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                // 4. Canal de Suporte (Feature Flag: checkout_support_channel)
                state.supportWhatsapp?.let { whatsappNumber ->
                    item {
                        OutlinedButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse("https://wa.me/$whatsappNumber")
                                }
                                context.startActivity(intent)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.HelpOutline, contentDescription = null)
                            Text("Precisa de ajuda? Chame no WhatsApp", modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }

                // Botão Finalizar
                item {
                    Button(
                        onClick = { onEvent(CheckoutUiEvent.OnPlaceOrderClick) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(top = 8.dp)
                    ) {
                        Text("Confirmar Pedido - R$ ${state.totalPrice}", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
