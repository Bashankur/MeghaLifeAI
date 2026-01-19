package com.meghalife.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryGreen,
    secondary = AccentBlue,
    error = DangerRed,
    surface = SurfaceLight
)

@Composable
fun MeghaLifeAITheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
