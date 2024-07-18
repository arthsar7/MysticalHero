package com.example.mysticalhero.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mysticalhero.ui.destinations.FinishScreen
import com.example.mysticalhero.ui.destinations.GameScreen
import com.example.mysticalhero.ui.destinations.MenuScreen
import com.example.mysticalhero.ui.destinations.SettingsScreen
import com.example.mysticalhero.ui.destinations.SplashScreen
import com.example.mysticalhero.utils.vibrate

@Composable
fun AppNavigation(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = AppDestinations.SPLASH
    ) {
        composable(AppDestinations.SPLASH) {
            SplashScreen {
                navController.navigate(AppDestinations.MENU) {
                    popUpTo(AppDestinations.SPLASH) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }
        composable(AppDestinations.MENU) {
            MenuScreen(
                onStartClick = {
                    context.vibrate()
                    navController.navigate(AppDestinations.GAME) {
                        launchSingleTop = true
                    }
                },
                onSettingsClick = {
                    context.vibrate()
                    navController.navigate(AppDestinations.SETTINGS) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(AppDestinations.GAME) {
            GameScreen(
                onNewGameClick = {
                    context.vibrate()
                    navController.popBackStack()
                },
                onFinishGame = { result ->
                    navController.navigate("${AppDestinations.FINISH}/$result") {
                        popUpTo(AppDestinations.GAME) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(
            route = "${AppDestinations.FINISH}/{result}",
            arguments = listOf(navArgument("result") { type = NavType.BoolType })
        ) {
            val result = it.arguments?.getBoolean("result") ?: false
            FinishScreen(result) {
                context.vibrate()
                navController.navigate(AppDestinations.MENU) {
                    popUpTo(AppDestinations.MENU) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }
        composable(AppDestinations.SETTINGS) {
            SettingsScreen(
                onBackClick = {
                    context.vibrate()
                    navController.popBackStack()
                }
            )
        }
    }
}