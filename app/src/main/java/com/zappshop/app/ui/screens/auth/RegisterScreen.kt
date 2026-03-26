package com.zappshop.app.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onGoToLogin: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    // CORREÇÃO: Coleta o estado do ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // CORREÇÃO: Usa isSuccess em vez de success para bater com o AuthUiState
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onRegisterSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Criar conta", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(32.dp))

        // Campo Nome - Conectado ao ViewModel
        OutlinedTextField(
            value = uiState.fullName,
            onValueChange = { viewModel.onFullNameChange(it) },
            label = { Text("Nome Completo") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        )
        Spacer(Modifier.height(12.dp))

        // Campo E-mail - Conectado ao ViewModel
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        )
        Spacer(Modifier.height(12.dp))

        // Campo Senha - Conectado ao ViewModel
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        )
        Spacer(Modifier.height(8.dp))

        // Exibição de Erro
        uiState.error?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(8.dp))
        }

        // Botão Cadastrar
        Button(
            onClick = { viewModel.register() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Cadastrar")
            }
        }

        Spacer(Modifier.height(12.dp))

        TextButton(
            onClick = onGoToLogin,
            enabled = !uiState.isLoading
        ) {
            Text("Já tenho conta. Fazer login")
        }
    }
}