package com.sraccelerator.easyorder.hamburgueria.core

import com.sraccelerator.easyorder.core.config.AppConfig
import com.sraccelerator.easyorder.hamburgueria.core.theme.HamburgueriaPalette
import javax.inject.Inject

class HamburgueriaConfig @Inject constructor() : AppConfig {
    override val restaurantId: Int = 1
    override val appName: String = "Hamburgueria do Lucas"
    override val colors = HamburgueriaPalette
}