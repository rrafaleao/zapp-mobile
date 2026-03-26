package com.zappshop.app.ui.screens.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    onBack: () -> Unit,
    onGoToCart: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Carrega o produto assim que a tela abre
    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    // Navega para o carrinho se o produto foi adicionado com sucesso
    if (uiState.addedToCart) {
        LaunchedEffect(Unit) {
            onGoToCart()
            viewModel.resetCartStatus()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else if (uiState.error != null) {
                Text(text = uiState.error!!, color = MaterialTheme.colorScheme.error)
            } else {
                uiState.product?.let { product ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        AsyncImage(
                            model = product.imageUrl ?: product.image,
                            contentDescription = product.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(product.name, style = MaterialTheme.typography.headlineMedium)
                        Text("R$ ${product.price}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.height(16.dp))
                        Text(product.description ?: "Sem descrição disponível.")

                        Spacer(Modifier.height(32.dp))
                        Button(
                            onClick = { viewModel.addToCart() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Adicionar ao Carrinho")
                        }
                    }
                }
            }
        }
    }
}