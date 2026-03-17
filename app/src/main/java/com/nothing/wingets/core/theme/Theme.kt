package com.nothing.wingets.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val NothingColorScheme = darkColorScheme(
    primary = NothingWhite,
    secondary = NothingAccent,
    background = NothingBlack,
    surface = NothingGray,
    onPrimary = NothingBlack,
    onSecondary = NothingBlack,
    onBackground = NothingWhite,
    onSurface = NothingWhite
)

@Composable
fun NothingWingetsTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSpacing provides AppSpacing()) {
        MaterialTheme(
            colorScheme = NothingColorScheme,
            typography = NothingTypography,
            content = content
        )
    }
}
