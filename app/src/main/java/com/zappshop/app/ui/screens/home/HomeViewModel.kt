package com.zappshop.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadProducts()
    }

    fun onSearchChange(query: String) {
        _searchQuery.value = query
        loadProducts(query)
    }

    fun loadProducts(search: String? = null) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // CORREÇÃO: Usando apenas os parâmetros que existem no Repository (search, page)
            repository.getProducts(search = search, page = 1)
                .onSuccess { products ->
                    _uiState.update {
                        it.copy(products = products, isLoading = false, error = null)
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(error = exception.message, isLoading = false)
                    }
                }
        }
    }
}