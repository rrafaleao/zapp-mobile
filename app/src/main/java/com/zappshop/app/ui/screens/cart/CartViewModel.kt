package com.zappshop.app.ui.screens.cart

import androidx.lifecycle.ViewModel
import com.zappshop.app.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    val uiState = repository.items
    private val _checkoutSuccess = MutableStateFlow(false)
    val checkoutSuccess: StateFlow<Boolean> = _checkoutSuccess.asStateFlow()

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
        _checkoutSuccess.value = true
    }

    fun consumeCheckoutSuccess() {
        _checkoutSuccess.value = false
    }
}