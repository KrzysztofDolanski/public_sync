package com.krzysztof.twopublicsync.game

import android.graphics.Bitmap

data class GameState(
    val currentTask: Task? = null,
    val performerReady: Boolean = false,
    val verifierReady: Boolean = false,
    val lastPhoto: Bitmap? = null,
    val gestureCorrect: Boolean? = null
)
