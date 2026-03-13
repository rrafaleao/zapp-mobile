package com.zappshop.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappshop.app.data.model.Category
import com.zappshop.app.data.model.Product
import com.zappshop.app.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val featuredProducts: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    val searchQuery = MutableStateFlow("")
    val selectedCategory = MutableStateFlow<String?>(null)
    val selectedSort = MutableStateFlow<String?>(null)

    init {
        loadAll()
    }

    private fun loadAll() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(isLoading = true)

            val productsResult = repository.getProducts()
            val featuredResult = repository.getFeaturedProducts()
            val categoriesResult = repository.getCategories()

            _uiState.value = HomeUiState(
                products = productsResult.getOrDefault(emptyList()),
                featuredProducts = featuredResult.getOrDefault(emptyList()),
                categories = categoriesResult.getOrDefault(emptyList()),
                isLoading = false,
                error = productsResult.exceptionOrNull()?.message
            )
        }
    }

    fun loadProducts() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            val result = repository.getProducts(
                search = searchQuery.value.ifBlank { null },
                category = selectedCategory.value,
                sortBy = selectedSort.value
            )

            _uiState.value = _uiState.value.copy(
                products = result.getOrDefault(emptyList()),
                isLoading = false,
                error = result.exceptionOrNull()?.message
            )
        }
    }

    fun onSearchChange(query: String) {
        searchQuery.value = query
        loadProducts()
    }

    fun onCategorySelected(category: String?) {
        selectedCategory.value = category
        loadProducts()
    }

    fun onSortSelected(sort: String?) {
        selectedSort.value = sort
        loadProducts()
    }
}