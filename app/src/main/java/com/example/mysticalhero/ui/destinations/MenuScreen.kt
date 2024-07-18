package com.example.mysticalhero.ui.destinations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysticalhero.utils.PrefsManager
import com.example.mysticalhero.R

@Composable
fun MenuScreen(
    onStartClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg_game),
                contentScale = ContentScale.Crop
            )
    ) {
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xff1C1C1C),
                ),
                shape = CircleShape,
                border = BorderStroke(2.dp, red),
                modifier = Modifier
            ) {
                Text(
                    text = "High Score: ${PrefsManager.highScore}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black
                )
            }
            IconButton(
                onClick = onStartClick,
                modifier = Modifier
                    .height(40.dp)
                    .width(210.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.start_btn),
                    contentDescription = null,
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp),
                    tint = Color.Unspecified,
                )
            }
        }
    }
}