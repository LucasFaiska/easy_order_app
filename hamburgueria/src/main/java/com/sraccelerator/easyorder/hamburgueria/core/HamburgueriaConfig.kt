package com.sraccelerator.easyorder.hamburgueria.core

import com.sraccelerator.easyorder.core.config.AppConfig
import javax.inject.Inject

class HamburgueriaConfig @Inject constructor() : AppConfig {
    override val restaurantId: Int = 1
    override val appName: String = "Hamburgueria do Lucas"
}