package com.sraccelerator.easyorder.data

import com.sraccelerator.easyorder.data.model.CartItem
import com.sraccelerator.easyorder.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CartRepository {
    val cartItems: Flow<List<CartItem>>
    val totalItemsCount: Flow<Int>
    fun addToCart(product: Product)
    fun removeFromCart(productId: Int)
    fun clear()
}

internal class CartRepositoryImpl @Inject constructor() : CartRepository {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems = _cartItems.asStateFlow()

    override val totalItemsCount = _cartItems.map { items ->
        items.sumOf { it.quantity }
    }

    override fun addToCart(product: Product) {
        val currentList = _cartItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.product.id == product.id }

        if (index != -1) {
            currentList[index] = currentList[index].copy(quantity = currentList[index].quantity + 1)
        } else {
            currentList.add(CartItem(product, 1))
        }
        _cartItems.value = currentList
    }

    override fun removeFromCart(productId: Int) {
        val currentList = _cartItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.product.id == productId }

        if (index != -1) {
            val item = currentList[index]
            if (item.quantity > 1) {
                currentList[index] = item.copy(quantity = item.quantity - 1)
            } else {
                currentList.removeAt(index)
            }
            _cartItems.value = currentList
        }
    }

    override fun clear() {
        _cartItems.value = emptyList()
    }
}