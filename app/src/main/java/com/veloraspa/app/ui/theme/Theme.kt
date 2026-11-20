package com.veloraspa.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White

private val VeloraColorScheme = lightColorScheme(
    primary = DarkBrown,
    secondary = SoftBrown,
    background = PinkBackground,
    surface = CardBackground,
    onPrimary = White,
    onSecondary = White,
    onBackground = DarkBrown,
    onSurface = DarkBrown
)

@Composable
fun VeloraspaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VeloraColorScheme,
        typography = AppTypography,
        content = content
    )
}