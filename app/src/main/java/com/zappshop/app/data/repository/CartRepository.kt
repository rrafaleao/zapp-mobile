package com.zappshop.app.data.repository

import com.zappshop.app.data.model.CartItem
import com.zappshop.app.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items

    fun addItem(product: Product) {
        val current = _items.value.toMutableList()
        val existing = current.indexOfFirst { it.product.id == product.id }
        if (existing != -1) {
            current[existing] = current[existing].copy(quantity = current[existing].quantity + 1)
        } else {
            current.add(CartItem(product = product, quantity = 1))
        }
        _items.value = current
    }

    fun removeItem(productId: String) { // STRING
        _items.value = _items.value.filter { it.product.id != productId }
    }

    fun updateQuantity(productId: String, quantity: Int) { // STRING
        if (quantity <= 0) {
            removeItem(productId)
            return
        }
        _items.value = _items.value.map {
            if (it.product.id == productId) it.copy(quantity = quantity) else it
        }
    }

    fun getTotal(): Double {
        return _items.value.sumOf { it.product.price * it.quantity }
    }

    fun clearCart() {
        _items.value = emptyList()
    }
}