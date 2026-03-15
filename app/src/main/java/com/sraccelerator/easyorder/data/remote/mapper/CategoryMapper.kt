package com.sraccelerator.easyorder.data.remote.mapper

import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.remote.dto.response.CategoryDTO

internal fun CategoryDTO.toModel(): Category {
    return Category(
        id = id ?: 0,
        name = name.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
        displayOrder = displayOrder ?: 0,
        isActive = status?.equals("active", ignoreCase = true) ?: false
    )
}