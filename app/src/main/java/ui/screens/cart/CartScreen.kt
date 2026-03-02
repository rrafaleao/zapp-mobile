package com.zappshop.app.ui.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onBack: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val items by viewModel.items.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrinho") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Voltar") } }
            )
        },
        bottomBar = {
            if (items.isNotEmpty()) {
                Surface(shadowElevation = 8.dp) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Total: R$ %.2f".format(viewModel.getTotal()), style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { /* TODO: checkout */ }, modifier = Modifier.fillMaxWidth()) {
                            Text("Finalizar pedido")
                        }
                    }
                }
            }
        }
    ) { padding ->
        if (items.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Seu carrinho está vazio")
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(12.dp), modifier = Modifier.padding(padding)) {
                items(items) { cartItem ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(cartItem.product.name, style = MaterialTheme.typography.titleSmall)
                                Text("R$ %.2f".format(cartItem.product.price), color = MaterialTheme.colorScheme.primary)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = { viewModel.updateQuantity(cartItem.product.id, cartItem.quantity - 1) }) { Text("-") }
                                Text("${cartItem.quantity}")
                                IconButton(onClick = { viewModel.updateQuantity(cartItem.product.id, cartItem.quantity + 1) }) { Text("+") }
                                IconButton(onClick = { viewModel.removeItem(cartItem.product.id) }) {
                                    Icon(Icons.Default.Delete, "Remover")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}