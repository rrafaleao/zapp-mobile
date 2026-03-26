package com.zappshop.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zappshop.app.ui.navigation.Screen

@Composable
fun BottomNavBar(navController: NavController) {
    // Lista das telas que aparecerão na barra inferior
    val items = listOf(
        Screen.Home,
        Screen.Cart,
        Screen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { screen ->
            // Verifica se a rota atual é a mesma desta aba
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                icon = {
                    when (screen) {
                        is Screen.Home -> Icon(Icons.Default.Home, contentDescription = null)
                        is Screen.Cart -> Icon(Icons.Default.ShoppingCart, contentDescription = null)
                        is Screen.Profile -> Icon(Icons.Default.Person, contentDescription = null)
                        else -> Icon(Icons.Default.Home, contentDescription = null)
                    }
                },
                label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        // Evita empilhar várias cópias da mesma tela
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}