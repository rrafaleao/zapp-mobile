package com.zappshop.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zappshop.app.ui.screens.auth.AuthViewModel
import com.zappshop.app.ui.screens.auth.LoginScreen
import com.zappshop.app.ui.screens.auth.RegisterScreen
import com.zappshop.app.ui.screens.home.HomeScreen

// ESTA CLASSE DEVE ESTAR AQUI NO TOPO
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object ProductDetail : Screen("product/{productId}") {
        fun createRoute(id: String) = "product/$id"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val token by authViewModel.token.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (token.isNullOrEmpty()) Screen.Login.route else Screen.Home.route
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
            HomeScreen(onProductClick = { /* navegação */ })
        }
    }
}