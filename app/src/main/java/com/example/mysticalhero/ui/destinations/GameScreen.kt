package com.example.mysticalhero.ui.destinations

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysticalhero.ui.PlaneCard
import com.example.mysticalhero.utils.PrefsManager
import com.example.mysticalhero.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.ranges.contains
import kotlin.ranges.until

@Composable
fun GameScreen(
    onNewGameClick: () -> Unit,
    onFinishGame: (Boolean) -> Unit
) {
    val cards = remember { generateCards() }
    var selectedCardIndices by remember { mutableStateOf<List<Int>>(emptyList()) }
    var matchedCardIndices by remember { mutableStateOf<List<Int>>(emptyList()) }
    var score by remember { mutableIntStateOf(0) }
    var time by remember { mutableLongStateOf(TimeUnit.MINUTES.toSeconds(5)) }
    var settingsClicked by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    fun checkHighScore() {
        if (score > PrefsManager.highScore) {
            PrefsManager.highScore = score
        }
    }
    LaunchedEffect(Unit) {
        while (time > 0) {
            delay(TimeUnit.SECONDS.toMillis(5))
            if (!settingsClicked) {
                time--
            }
        }
        onFinishGame(matchedCardIndices.size == 16)
        checkHighScore()
    }
    if (settingsClicked) {
        BackHandler {
            settingsClicked = false
        }
        SettingsScreen(
            onBackClick = {
                settingsClicked = false
            }
        )
    }
    else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = PrefsManager.background.resId),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    ),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                OutlinedCard(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 32.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, Color.White),
                    colors = CardDefaults.outlinedCardColors(
                        containerColor = Color(0xff1C1C1C),
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (row in 0 until 4) {
                            Row {
                                for (col in 0 until 4) {
                                    val cardIndex = row * 4 + col
                                    PlaneCard(
                                        card = cards[cardIndex],
                                        isSelected = selectedCardIndices.contains(cardIndex),
                                        isMatched = matchedCardIndices.contains(cardIndex),
                                        onClick = {
                                            if (selectedCardIndices.size < 2) {
                                                if (!selectedCardIndices.contains(
                                                        cardIndex
                                                    )
                                                ) {
                                                    selectedCardIndices += cardIndex
                                                }
                                                else {
                                                    selectedCardIndices -= cardIndex
                                                }
                                            }
                                            if (selectedCardIndices.size == 2) {
                                                if (cards[selectedCardIndices[0]] == cards[selectedCardIndices[1]]) {
                                                    matchedCardIndices += selectedCardIndices
                                                    score += when (time) {
                                                        in 0..60 -> 10
                                                        in 61..120 -> 16
                                                        in 121..180 -> 32
                                                        in 181..240 -> 48
                                                        else -> 64
                                                    }
                                                    if (matchedCardIndices.size == 16) {
                                                        onFinishGame(true)
                                                        checkHighScore()
                                                    }
                                                    selectedCardIndices = emptyList()
                                                }
                                                else {
                                                    scope.launch {
                                                        delay(600)
                                                        selectedCardIndices = emptyList()
                                                    }
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                    ) {
                        CircularProgressIndicator(
                            progress = 1 - time.toFloat() / TimeUnit.MINUTES.toSeconds(5),
                            modifier = Modifier
                                .padding(16.dp)
                                .size(150.dp),
                            color = red,
                            strokeWidth = 8.dp,
                            trackColor = Color.White
                        )
                        Text(
                            text = time.secondsToFormattedTime(),
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                    ) {
                        OutlinedCard(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xff1C1C1C),
                            ),
                            shape = CircleShape,
                            border = BorderStroke(2.dp, red)
                        ) {
                            Text(
                                text = "Score: $score",
                                color = Color.White,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier.padding(6.dp),
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
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
                                modifier = Modifier.padding(6.dp),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    IconButton(
                        onClick = onNewGameClick,
                        modifier = Modifier
                            .height(40.dp)
                            .width(210.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.btn_new_game),
                            contentDescription = null,
                            modifier = Modifier
                                .height(40.dp)
                                .width(200.dp),
                            tint = Color.Unspecified,
                        )
                    }
                }
            }
            IconButton(
                onClick = {
                    settingsClicked = true
                },
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
        }
    }
}

private fun Long.secondsToFormattedTime(): String {
    val minutes = this / 60
    val seconds = this % 60
    return "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaneCard(
    card: PlaneCard,
    isSelected: Boolean,
    isMatched: Boolean,
    onClick: () -> Unit
) {
    var flipped by remember { mutableStateOf(false) }
    val rotationedY by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600)
    )

    LaunchedEffect(isSelected, isMatched) {
        flipped = isSelected || isMatched
    }

    Card(
        modifier = Modifier
            .size(64.dp)
            .padding(4.dp)
            .graphicsLayer {
                rotationY = rotationedY
                cameraDistance = 8 * density
            },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        ),
        onClick = onClick
    ) {
        if (rotationedY > 90f) {
            Image(
                painter = painterResource(id = card.drawableId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
        }
    }
}

fun generateCards(): List<PlaneCard> {
    val cardValues = PlaneCard.entries.shuffled().take(8)
    val pairedCards = cardValues + cardValues
    return pairedCards.shuffled(Random(System.currentTimeMillis()))
}
