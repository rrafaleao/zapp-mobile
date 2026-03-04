package com.zappshop.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    var searchQuery = MutableStateFlow("")

    init { loadProducts() }

    fun loadProducts(search: String? = null) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val result = repository.getProducts(search)
            _uiState.value = if (result.isSuccess)
                HomeUiState(products = result.getOrDefault(emptyList()))
            else
                HomeUiState(error = result.exceptionOrNull()?.message)
        }
    }

    fun onSearchChange(query: String) {
        searchQuery.value = query
        loadProducts(query.ifBlank { null })
    }
}