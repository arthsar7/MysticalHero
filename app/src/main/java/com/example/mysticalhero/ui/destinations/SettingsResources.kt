package com.example.mysticalhero.ui.destinations

import androidx.compose.runtime.Stable
import com.example.mysticalhero.R
object SettingsResources {
    @Stable
    val backgrounds = listOf(
        Background(R.drawable.classic_bg, "Classic"),
        Background(R.drawable.lightning_bg, "Thunderstorm"),
        Background(R.drawable.sky_bg, "Blue sky"),
    )
    data class Background(val resId: Int, val name: String) {
        companion object {
            fun valueOf(name: String): Background {
                return backgrounds.first { it.name == name }
            }
        }
    }
}