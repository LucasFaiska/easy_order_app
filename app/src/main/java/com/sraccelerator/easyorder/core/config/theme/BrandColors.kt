package com.sraccelerator.easyorder.core.config.theme

import androidx.compose.ui.graphics.Color

data class BrandColors(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val error: Color = Color(0xFFBA1A1A),
    val onError: Color = Color(0xFFFFFFFF),
    val success: Color = Color(0xFF2E7D32),
    val onSuccess: Color = Color(0xFFFFFFFF)
)