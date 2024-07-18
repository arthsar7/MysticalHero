package com.example.mysticalhero.ui.destinations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mysticalhero.utils.PrefsManager
import com.example.mysticalhero.R
val red = Color(0xFFCA2121)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
) {
    var currentBg by remember { mutableStateOf(PrefsManager.background) }
    var vibrationChecked by remember { mutableStateOf(PrefsManager.vibrationChecked) }
    LaunchedEffect(currentBg) {
        PrefsManager.background = currentBg
    }
    LaunchedEffect(vibrationChecked) {
        PrefsManager.vibrationChecked = vibrationChecked
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = currentBg.resId),
                contentScale = ContentScale.Crop
            )
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SettingsResources.backgrounds.forEach {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = it.name, color = Color.White)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedCard(
                        onClick = { currentBg = it },
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20),
                        border = BorderStroke(1.dp, red)
                    ) {
                        Image(
                            painter = painterResource(id = it.resId),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    IconButton(onClick = {
                        currentBg = it
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (currentBg == it) {
                                    R.drawable.ic_active_toggle
                                }
                                else {
                                    R.drawable.ic_nonactive_toggle
                                }
                            ),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
                .height(40.dp)
                .width(200.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.35f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Vibration",
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = vibrationChecked,
                    onCheckedChange = { vibrationChecked = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = red,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0xFFD9DBE9)
                    )
                )
            }
        }

    }
}