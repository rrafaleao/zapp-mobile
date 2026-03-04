package com.zappshop.app.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappshop.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    val token = repository.getToken()
    val userName = repository.getUserName()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            val result = repository.login(email, password)
            _uiState.value = if (result.isSuccess) AuthUiState(success = true)
            else AuthUiState(error = result.exceptionOrNull()?.message)
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            val result = repository.register(name, email, password)
            _uiState.value = if (result.isSuccess) AuthUiState(success = true)
            else AuthUiState(error = result.exceptionOrNull()?.message)
        }
    }

    fun logout() {
        viewModelScope.launch { repository.logout() }
    }
}