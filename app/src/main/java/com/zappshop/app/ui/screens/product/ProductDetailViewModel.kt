package com.zappshop.app.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.repository.CartRepository
import com.zappshop.app.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    fun loadProduct(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            productRepository.getProductById(id).fold(
                onSuccess = { product ->
                    _uiState.update { it.copy(product = product, isLoading = false) }
                },
                onFailure = { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message) }
                }
            )
        }
    }

    fun addToCart() {
        _uiState.value.product?.let { product ->
            viewModelScope.launch {
                cartRepository.addItem(product)
                _uiState.update { it.copy(addedToCart = true) }
            }
        }
    }

    fun resetCartStatus() {
        _uiState.update { it.copy(addedToCart = false) }
    }
}