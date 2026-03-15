package com.sraccelerator.easyorder.data.remote.mapper

import com.sraccelerator.easyorder.data.model.Product
import com.sraccelerator.easyorder.data.remote.dto.response.ProductDTO

internal fun ProductDTO.toModel(): Product {
    return Product(
        id = id ?: 0,
        name = name.orEmpty(),
        description = description.orEmpty(),
        price = price ?: 0.0,
        originalPrice = originalPrice,
        imageUrl = imageUrl.orEmpty(),
        isAvailable = isAvailable ?: false,
        isPromoted = isPromoted ?: false,
        rating = rating ?: 0.0,
        preparationTime = preparationTime.orEmpty(),
        tags = tags ?: emptyList()
    )
}