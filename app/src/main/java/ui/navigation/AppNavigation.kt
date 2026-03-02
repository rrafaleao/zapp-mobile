package com.zappshop.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zappshop.app.data.local.SessionManager
import com.zappshop.app.ui.components.BottomNavBar
import com.zappshop.app.ui.screens.auth.AuthViewModel
import com.zappshop.app.ui.screens.auth.LoginScreen
import com.zappshop.app.ui.screens.auth.RegisterScreen
import com.zappshop.app.ui.screens.cart.CartScreen
import com.zappshop.app.ui.screens.home.HomeScreen
import com.zappshop.app.ui.screens.product.ProductDetailScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object ProductDetail : Screen("product/{productId}") {
        fun createRoute(id: Int) = "product/$id"
    }
    object Cart : Screen("cart")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val token by authViewModel.token.collectAsState(initial = null)

    val startDestination = if (token.isNullOrEmpty()) Screen.Login.route else Screen.Home.route
    val showBottomBar = token != null

    Scaffold(
        bottomBar = {
            if (showBottomBar) BottomNavBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginSuccess = { navController.navigate(Screen.Home.route) { popUpTo(0) } },
                    onGoToRegister = { navController.navigate(Screen.Register.route) }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    onRegisterSuccess = { navController.navigate(Screen.Home.route) { popUpTo(0) } },
                    onGoToLogin = { navController.popBackStack() }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(onProductClick = { id ->
                    navController.navigate(Screen.ProductDetail.createRoute(id))
                })
            }
            composable(
                route = Screen.ProductDetail.route,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
                ProductDetailScreen(
                    productId = productId,
                    onBack = { navController.popBackStack() },
                    onGoToCart = { navController.navigate(Screen.Cart.route) }
                )
            }
            composable(Screen.Cart.route) {
                CartScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}