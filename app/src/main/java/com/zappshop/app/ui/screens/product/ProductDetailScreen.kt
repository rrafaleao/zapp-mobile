package com.zappshop.app.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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

    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    LaunchedEffect(uiState.addedToCart) {
        if (uiState.addedToCart) {
            onGoToCart()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Favoritar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color(0xFFF8F9FC)
    ) { padding ->

        when {
            uiState.isLoading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF4B8BF4))
                }
            }

            uiState.error != null -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("😕", fontSize = 40.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            uiState.error!!,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            uiState.product != null -> {
                val product = uiState.product!!

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(Color(0xFFEEF2FF))
                    ) {
                        AsyncImage(
                            model = product.imageUrl ?: "", // CORREÇÃO: Tratando nulo
                            contentDescription = product.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .align(Alignment.BottomCenter)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, Color(0xFFF8F9FC))
                                    )
                                )
                        )
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-20).dp),
                        shape = RoundedCornerShape(24.dp, 24.dp),
                        color = Color.White,
                        shadowElevation = 4.dp
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {

                            Text(
                                "Vendido por ${product.storeName ?: "Loja Parceira"}", // CORREÇÃO: Tratando nulo
                                fontSize = 11.sp,
                                color = Color(0xFF888888),
                                fontWeight = FontWeight.Medium
                            )

                            Spacer(Modifier.height(6.dp))

                            Text(
                                product.name,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF111111)
                            )

                            Spacer(Modifier.height(10.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("★★★★★", color = Color(0xFFF59E0B), fontSize = 13.sp)
                                Spacer(Modifier.width(6.dp))
                                Text("4.8", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
                                Spacer(Modifier.width(4.dp))
                                Text("(128 avaliações)", fontSize = 11.sp, color = Color(0xFF999999))
                            }

                            Spacer(Modifier.height(14.dp))
                            Divider(color = Color(0xFFF0F0F0))
                            Spacer(Modifier.height(14.dp))

                            if (product.promotion != null) {
                                Row(verticalAlignment = Alignment.Bottom) {
                                    Text(
                                        "R$ %.2f".format(product.promotion.promoPrice),
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.Black,
                                        color = Color(0xFF4B8BF4)
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        "R$ %.2f".format(product.promotion.originalPrice),
                                        fontSize = 14.sp,
                                        color = Color(0xFFBBBBBB),
                                        textDecoration = TextDecoration.LineThrough,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                }
                                Spacer(Modifier.height(4.dp))
                                Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFFEF9C3)) {
                                    Text(
                                        "🏷️ ${product.promotion.name} — ${product.promotion.discountPercent}% OFF",
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                        fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF92400E)
                                    )
                                }
                            } else {
                                Text(
                                    "R$ %.2f".format(product.price),
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color(0xFF4B8BF4)
                                )
                            }

                            Spacer(Modifier.height(8.dp))
                            Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFDCFCE7)) {
                                Text(
                                    "✓  Em estoque",
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF16A34A)
                                )
                            }

                            Spacer(Modifier.height(20.dp))
                            Text("Descrição", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF111111))
                            Spacer(Modifier.height(6.dp))

                            Text(
                                product.description ?: "Sem descrição disponível.", // CORREÇÃO: Tratando nulo
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF555555),
                                lineHeight = 22.sp
                            )

                            Spacer(Modifier.height(28.dp))

                            Button(
                                onClick = { viewModel.addToCart() },
                                modifier = Modifier.fillMaxWidth().height(52.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B8BF4))
                            ) {
                                Text("Adicionar ao carrinho", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}