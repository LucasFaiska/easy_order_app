package com.sraccelerator.easyorder.data.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val originalPrice: Double?,
    val imageUrl: String,
    val isAvailable: Boolean,
    val isPromoted: Boolean,
    val rating: Double,
    val preparationTime: String,
    val tags: List<String>
)