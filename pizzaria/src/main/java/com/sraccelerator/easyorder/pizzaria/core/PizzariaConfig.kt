package com.sraccelerator.easyorder.pizzaria.core

import com.sraccelerator.easyorder.core.config.AppConfig
import javax.inject.Inject

class PizzariaConfig @Inject constructor() : AppConfig {
    override val restaurantId: Int = 2
    override val appName: String = "Pizzaria do Lucas"
}
