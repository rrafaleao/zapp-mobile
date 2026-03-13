package com.zappshop.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zappshop.app.ui.components.ProductCard

@Composable
fun HomeScreen(
    // CORREÇÃO: Alterado de Int para String para suportar o UUID do banco
    onProductClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val search by viewModel.searchQuery.collectAsState()
    var selectedCategory by remember { mutableStateOf("Tudo") }
    val categories = listOf("Tudo", "Eletrônico", "Moda", "Casa")

    Scaffold(containerColor = Color(0xFFF8F9FC)) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // ── Header ───────────────────────────────────────────────────────────
            Surface(color = Color.White, shadowElevation = 2.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            buildAnnotatedString {
                                append("Minha")
                                withStyle(SpanStyle(color = Color(0xFF4B8BF4))) { append("Loja") }
                            },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF111111)
                        )
                        Text(
                            "Bom dia, visitante! 👋",
                            fontSize = 11.sp,
                            color = Color(0xFF888888)
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4B8BF4))
                    ) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Notificações",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            // ── Search bar ───────────────────────────────────────────────────────
            OutlinedTextField(
                value = search,
                onValueChange = { viewModel.onSearchChange(it) },
                placeholder = {
                    Text("Buscar produtos...", fontSize = 13.sp, color = Color(0xFFBBBBBB))
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFFAAAAAA))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFEEEEEE),
                    focusedBorderColor = Color(0xFF4B8BF4),
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                singleLine = true
            )

            Spacer(Modifier.height(14.dp))

            // ── Offer banner ─────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.horizontalGradient(listOf(Color(0xFF4B8BF4), Color(0xFF3B6FD4)))
                    )
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "OFERTA DA SEMANA",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.8f),
                            letterSpacing = 2.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Até 50%\nde desconto",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            lineHeight = 26.sp
                        )
                        Spacer(Modifier.height(10.dp))
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White.copy(alpha = 0.25f)
                        ) {
                            Text(
                                "Ver ofertas →",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                    Text("🛍", fontSize = 40.sp)
                }
            }

            Spacer(Modifier.height(14.dp))

            // ── Category chips ───────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    val isSelected = category == selectedCategory
                    Surface(
                        onClick = {
                            selectedCategory = category
                            viewModel.onSearchChange(if (category == "Tudo") "" else category)
                        },
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) Color(0xFF4B8BF4) else Color.White,
                        shadowElevation = if (isSelected) 0.dp else 1.dp
                    ) {
                        Text(
                            category,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSelected) Color.White else Color(0xFF555555)
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            // ── Section header ───────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Em destaque",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111111)
                )
                TextButton(onClick = {}) {
                    Text(
                        "Ver todos",
                        fontSize = 12.sp,
                        color = Color(0xFF4B8BF4),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            // ── Products grid / states ───────────────────────────────────────────
            when {
                uiState.isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF4B8BF4))
                    }
                }
                uiState.error != null -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.products) { product ->
                            ProductCard(
                                product = product,
                                onClick = { onProductClick(product.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}