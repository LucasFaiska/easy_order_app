package com.sraccelerator.easyorder.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sraccelerator.easyorder.data.model.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val displayOrder: Int,
    val isActive: Boolean
)

fun CategoryEntity.toModel() = Category(
    id = id,
    name = name,
    imageUrl = imageUrl,
    displayOrder = displayOrder,
    isActive = isActive
)

fun Category.toEntity() = CategoryEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    displayOrder = displayOrder,
    isActive = isActive
)
