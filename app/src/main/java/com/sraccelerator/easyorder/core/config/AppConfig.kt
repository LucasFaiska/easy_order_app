package com.sraccelerator.easyorder.core.config

import com.sraccelerator.easyorder.core.config.theme.BrandColors

interface AppConfig {
    val restaurantId: Int
    val appName: String
    val colors: BrandColors
}
