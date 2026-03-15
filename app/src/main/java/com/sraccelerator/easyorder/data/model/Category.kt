package com.sraccelerator.easyorder.data.model

data class Category(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val displayOrder: Int,
    val isActive: Boolean
)