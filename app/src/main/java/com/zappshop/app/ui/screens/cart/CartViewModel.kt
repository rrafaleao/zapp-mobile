package com.zappshop.app.ui.screens.cart

import androidx.lifecycle.ViewModel
import com.zappshop.app.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    val uiState = repository.items

    fun getTotalPrice(): Double {
        return repository.getTotal()
    }

    fun updateQuantity(productId: String, quantity: Int) { // STRING
        repository.updateQuantity(productId, quantity)
    }

    fun removeItem(productId: String) { // STRING
        repository.removeItem(productId)
    }

    fun checkout() {
        repository.clearCart()
    }
}