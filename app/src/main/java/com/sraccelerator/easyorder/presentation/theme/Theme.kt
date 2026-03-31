package com.sraccelerator.easyorder.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.sraccelerator.easyorder.core.config.AppConfig

/*
private val ColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    background = Background,
    onBackground = OnBackground,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    onError = OnError,
)
 */

@Composable
fun EasyOrderTheme(
    // darkTheme: Boolean = isSystemInDarkTheme(),
    config: AppConfig,
    content: @Composable () -> Unit
) {
    val brand = config.colors

    val colorScheme = lightColorScheme(
        primary = brand.primary,
        onPrimary = brand.onPrimary,
        primaryContainer = brand.primaryContainer,
        onPrimaryContainer = brand.onPrimaryContainer,
        secondary = brand.secondary,
        onSecondary = brand.onSecondary,
        secondaryContainer = brand.secondaryContainer,
        background = brand.background,
        onBackground = brand.onBackground,
        surface = brand.surface,
        onSurface = brand.onSurface,
        surfaceVariant = brand.surfaceVariant,
        onSurfaceVariant = brand.onSurfaceVariant,
        outline = brand.outline,
        onError = brand.onError,
        error = brand.error
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}