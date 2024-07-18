package com.example.mysticalhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.mysticalhero.ui.AppNavigation
import com.example.mysticalhero.ui.theme.MysticalHeroTheme
import com.example.mysticalhero.utils.PrefsManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        PrefsManager.init(this)
        setContent {
            val navController = rememberNavController()
            MysticalHeroTheme {
                AppNavigation(navController)
            }
        }
    }
}
