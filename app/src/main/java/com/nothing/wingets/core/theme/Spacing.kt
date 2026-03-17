package com.nothing.wingets.core.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSpacing(
    val x1: Dp = 4.dp,
    val x2: Dp = 8.dp,
    val x3: Dp = 12.dp,
    val x4: Dp = 16.dp,
    val x5: Dp = 20.dp,
    val x6: Dp = 24.dp
)

val LocalSpacing = staticCompositionLocalOf { AppSpacing() }
