package com.example.mysticalhero

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mysticalhero.destinations.FinishScreen
import com.example.mysticalhero.destinations.GameScreen
import com.example.mysticalhero.destinations.MenuScreen
import com.example.mysticalhero.destinations.ShopScreen
import com.example.mysticalhero.destinations.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.SPLASH
    ) {
        composable(AppDestinations.SPLASH) {
            SplashScreen()
        }
        composable(AppDestinations.MENU) {
            MenuScreen()
        }
        composable(AppDestinations.GAME) {
            GameScreen()
        }
        composable("${AppDestinations.FINISH}/{result}") {
            FinishScreen()
        }
        composable(AppDestinations.SHOP) {
            ShopScreen()
        }
    }
}