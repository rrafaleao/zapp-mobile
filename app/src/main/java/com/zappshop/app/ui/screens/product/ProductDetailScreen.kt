package com.zappshop.app.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                title = { Text("Detalhes do Produto") },
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
                        Card(
                            shape = RoundedCornerShape(22.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(320.dp)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0xFFF5F7FB),
                                                Color(0xFFE7ECF7)
                                            )
                                        )
                                    )
                            ) {
                                val imageModel = product.imageUrl ?: product.image
                                if (imageModel.isNullOrBlank()) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Sem imagem",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = Color(0xFF6D7788)
                                        )
                                    }
                                } else {
                                    AsyncImage(
                                        model = imageModel,
                                        contentDescription = product.name ?: "Produto",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(18.dp))

                        Text(
                            text = product.name ?: "Produto sem nome",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(Modifier.height(8.dp))

                        Surface(
                            color = Color(0xFFEDF4FF),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Text(
                                text = "R$ %.2f".format(product.price),
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                style = MaterialTheme.typography.titleLarge,
                                color = Color(0xFF1E63D6),
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        Text(
                            text = "Descrição",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = product.description ?: "Sem descrição disponível.",
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 22.sp
                        )

                        Spacer(Modifier.height(28.dp))

                        Button(
                            onClick = { viewModel.addToCart() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Adicionar ao Carrinho", fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}