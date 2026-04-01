package com.sraccelerator.easyorder.core.config

import androidx.compose.ui.graphics.Color
import com.sraccelerator.easyorder.core.config.theme.BrandColors

object MockAppConfig : AppConfig {
    override val restaurantId: Int = 1
    override val appName: String = "Mock App"
    override val colors: BrandColors = BrandColors(
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
        onError = Color(0xFFBA1A1A),
        error = Color(0xFFBA1A1A)
    )
}
