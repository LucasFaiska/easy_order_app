package com.sraccelerator.easyorder.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sraccelerator.easyorder.data.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val originalPrice: Double?,
    val imageUrl: String,
    val isAvailable: Boolean,
    val isPromoted: Boolean,
    val rating: Double,
    val preparationTime: String,
    val tags: String // Salvaremos como string separada por vírgula ou JSON
)

fun ProductEntity.toModel() = Product(
    id = id,
    name = name,
    description = description,
    price = price,
    originalPrice = originalPrice,
    imageUrl = imageUrl,
    isAvailable = isAvailable,
    isPromoted = isPromoted,
    rating = rating,
    preparationTime = preparationTime,
    tags = if (tags.isEmpty()) emptyList() else tags.split(",")
)

fun Product.toEntity(categoryId: Int) = ProductEntity(
    id = id,
    categoryId = categoryId,
    name = name,
    description = description,
    price = price,
    originalPrice = originalPrice,
    imageUrl = imageUrl,
    isAvailable = isAvailable,
    isPromoted = isPromoted,
    rating = rating,
    preparationTime = preparationTime,
    tags = tags.joinToString(",")
)
