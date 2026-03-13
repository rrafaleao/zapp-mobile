package com.zappshop.app.ui.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
    onNavigateBack: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Meu Carrinho") })
        }
    ) { padding ->
        if (uiState.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Seu carrinho está vazio")
            }
        } else {
            Column(Modifier.padding(padding)) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(uiState) { cartItem ->
                        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = cartItem.product.name, style = MaterialTheme.typography.titleMedium)
                                    Text(text = "R$ ${cartItem.product.price}", style = MaterialTheme.typography.bodyMedium)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = { viewModel.updateQuantity(cartItem.product.id, cartItem.quantity - 1) }) {
                                        Text("-", style = MaterialTheme.typography.headlineSmall)
                                    }
                                    Text(text = cartItem.quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
                                    IconButton(onClick = { viewModel.updateQuantity(cartItem.product.id, cartItem.quantity + 1) }) {
                                        Icon(Icons.Default.Add, contentDescription = null)
                                    }
                                    IconButton(onClick = { viewModel.removeItem(cartItem.product.id) }) {
                                        Icon(Icons.Default.Delete, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                                    }
                                }
                            }
                        }
                    }
                }
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total:", style = MaterialTheme.typography.headlineSmall)
                            Text("R$ ${viewModel.getTotalPrice()}", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
                        }
                        Button(onClick = { viewModel.checkout() }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                            Text("Finalizar Compra")
                        }
                    }
                }
            }
        }
    }
}