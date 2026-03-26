package com.zappshop.app.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onGoToRegister: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) onLoginSuccess()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        uiState.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        ) {
            if (uiState.isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp))
            else Text("Entrar")
        }
        TextButton(onClick = onGoToRegister) { Text("Criar conta") }
    }
}