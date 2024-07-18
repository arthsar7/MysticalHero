package com.example.mysticalhero.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mysticalhero.ui.destinations.SettingsResources

object PrefsManager {
    private const val PREFS_NAME = "MysticalHeroPrefs"
    private const val PREFS_KEY_BACKGROUND = "background"
    private const val PREFS_KEY_VIBRATION = "vibration"
    private const val PREFS_KEY_HIGH_SCORE = "high_score"
    private lateinit var prefs: SharedPreferences

    var highScore: Int
        get() = prefs.getInt(PREFS_KEY_HIGH_SCORE, 0)
        set(value) {
            prefs.edit().putInt(PREFS_KEY_HIGH_SCORE, value).apply()
        }

    var background: SettingsResources.Background
        get() = SettingsResources.Background.valueOf(
            prefs.getString(
                PREFS_KEY_BACKGROUND,
                SettingsResources.backgrounds.first().name
            )!!
        )
        set(value) {
            prefs.edit().putString(PREFS_KEY_BACKGROUND, value.name).apply()
        }

    var vibrationChecked: Boolean
        get() = prefs.getBoolean(PREFS_KEY_VIBRATION, true)
        set(value) {
            prefs.edit().putBoolean(PREFS_KEY_VIBRATION, value).apply()
        }

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}