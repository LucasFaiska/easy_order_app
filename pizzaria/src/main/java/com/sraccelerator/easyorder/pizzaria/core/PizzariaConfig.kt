package com.sraccelerator.easyorder.pizzaria.core

import com.sraccelerator.easyorder.core.config.AppConfig
import com.sraccelerator.easyorder.pizzaria.core.theme.PizzariaPalette
import javax.inject.Inject

class PizzariaConfig @Inject constructor() : AppConfig {
    override val restaurantId: Int = 2
    override val appName: String = "Pizzaria do Lucas"
    override val colors = PizzariaPalette
}
