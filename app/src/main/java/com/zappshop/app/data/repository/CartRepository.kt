package com.zappshop.app.data.repository

import com.zappshop.app.data.model.CartItem
import com.zappshop.app.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

// Carrinho em memória — pode ser migrado para Room futuramente
@Singleton
class CartRepository @Inject constructor() {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items.asStateFlow()

    fun addItem(product: Product) {
        val current = _items.value.toMutableList()
        val existing = current.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            current.add(CartItem(product, 1))
        }
        _items.value = current
    }

    fun removeItem(productId: Int) {
        _items.value = _items.value.filter { it.product.id != productId }
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        if (quantity <= 0) { removeItem(productId); return }
        _items.value = _items.value.map {
            if (it.product.id == productId) it.copy(quantity = quantity) else it
        }
    }

    fun clearCart() { _items.value = emptyList() }

    fun getTotal(): Double = _items.value.sumOf { it.product.price * it.quantity }
}