package com.kafecito.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Un solo esquema base. Cada pantalla decide si usa fondo negro o blanco,
// tal como lo define el Figma (no depende del modo oscuro del teléfono).
private val KafeColors = lightColorScheme(
    primary = KafeBlack,
    onPrimary = KafeWhite,
    secondary = KafeGray,
    background = KafeWhite,
    surface = KafeWhite,
    onBackground = KafeBlack,
    onSurface = KafeBlack,
    error = ErrorRed
)

@Composable
fun KafecitoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = KafeColors,
        typography = Typography,
        content = content
    )
}
