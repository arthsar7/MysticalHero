package com.example.mysticalhero.utils

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator

@Suppress("DEPRECATION")
fun Context.vibrate() {
    if (!PrefsManager.vibrationChecked) return
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
    }
    else {
        vibrator.vibrate(100)
    }
}