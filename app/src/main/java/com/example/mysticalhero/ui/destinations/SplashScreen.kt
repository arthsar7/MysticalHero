package com.example.mysticalhero.ui.destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mysticalhero.R
import kotlinx.coroutines.delay

private const val DELAY_TIME = 2000L

@Composable
fun SplashScreen(onNextScreen: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        delay(DELAY_TIME)
        onNextScreen()
    }
    Image(
        painter = painterResource(id = R.drawable.bg_game),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}