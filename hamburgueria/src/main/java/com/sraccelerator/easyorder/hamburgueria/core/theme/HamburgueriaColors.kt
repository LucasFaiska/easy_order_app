package com.sraccelerator.easyorder.hamburgueria.core.theme

import androidx.compose.ui.graphics.Color
import com.sraccelerator.easyorder.core.config.theme.BrandColors

val HamburgueriaPalette = BrandColors(
    primary = Color(0xFFFF6D00),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDBCF),
    onPrimaryContainer = Color(0xFF321000),
    secondary = Color(0xFF765849),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFDBCF),
    background = Color(0xFFFFF8F6),
    onBackground = Color(0xFF221A15),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF221A15),
    surfaceVariant = Color(0xFFF5DED5),
    onSurfaceVariant = Color(0xFF53433C),
    outline = Color(0xFF85736B),
    // Caso sua data class BrandColors suporte as cores de status:
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    success = Color(0xFF2E7D32),
    onSuccess = Color(0xFFFFFFFF)
)