package com.sraccelerator.easyorder.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("originalPrice") val originalPrice: Double?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("isAvailable") val isAvailable: Boolean?,
    @SerializedName("isPromoted") val isPromoted: Boolean?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("preparationTime") val preparationTime: String?,
    @SerializedName("tags") val tags: List<String>?
)