package com.example.mysticalhero.ui.destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysticalhero.utils.PrefsManager
import com.example.mysticalhero.R

@Composable
fun FinishScreen(
    result: Boolean,
    onContinueClick: () -> Unit,
) {
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = PrefsManager.background.resId),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
    ) {
        if (result) {
            Text(
                text = "GREAT!",
                fontSize = 30.sp,
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.plane),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Fit
            )
            IconButton(
                onClick = onContinueClick,
                modifier = Modifier
                    .height(40.dp)
                    .width(210.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btn_continue),
                    contentDescription = null,
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp),
                    tint = Color.Unspecified,
                )
            }
        }
        else {
            Text(
                text = "TRY AGAIN",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(100.dp))
            IconButton(
                onClick = onContinueClick,
                modifier = Modifier
                    .height(40.dp)
                    .width(210.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btn_continue),
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