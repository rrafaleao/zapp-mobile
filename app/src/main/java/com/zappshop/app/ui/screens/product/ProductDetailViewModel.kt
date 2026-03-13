package com.zappshop.app.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.repository.CartRepository
import com.zappshop.app.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductDetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val addedToCart: Boolean = false
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    fun loadProduct(id: String) {
        viewModelScope.launch {
            _uiState.value = ProductDetailUiState(isLoading = true)

            val result = productRepository.getProductById(id)

            _uiState.value = if (result.isSuccess) {
                ProductDetailUiState(product = result.getOrNull())
            } else {
                ProductDetailUiState(error = result.exceptionOrNull()?.message)
            }
        }
    }

    fun addToCart() {
        _uiState.value.product?.let { product ->
            cartRepository.addItem(product)
            _uiState.value = _uiState.value.copy(addedToCart = true)
        }
    }
}