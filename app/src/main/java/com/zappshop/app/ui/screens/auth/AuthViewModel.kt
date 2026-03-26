package com.zappshop.app.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappshop.app.data.model.AuthUiState
import com.zappshop.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    val token = repository.token

    fun onEmailChange(v: String) = _uiState.update { it.copy(email = v) }
    fun onPasswordChange(v: String) = _uiState.update { it.copy(password = v) }
    fun onFullNameChange(v: String) = _uiState.update { it.copy(fullName = v) }
    fun onPhoneChange(v: String) = _uiState.update { it.copy(phone = v) }

    fun login() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            repository.login(_uiState.value.email, _uiState.value.password)
                .onSuccess { _uiState.update { it.copy(isLoading = false, isSuccess = true) } }
                .onFailure { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
        }
    }

    fun register() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val s = _uiState.value
            repository.register(s.fullName, s.email, s.phone, s.password)
                .onSuccess { _uiState.update { it.copy(isLoading = false, isSuccess = true) } }
                .onFailure { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
        }
    }
}