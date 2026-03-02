package com.zappshop.app.ui.screens.cart

import androidx.lifecycle.ViewModel
import com.zappshop.app.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    val items = cartRepository.items
    fun getTotal() = cartRepository.getTotal()
    fun removeItem(productId: Int) = cartRepository.removeItem(productId)
    fun updateQuantity(productId: Int, quantity: Int) = cartRepository.updateQuantity(productId, quantity)
    fun clearCart() = cartRepository.clearCart()
}