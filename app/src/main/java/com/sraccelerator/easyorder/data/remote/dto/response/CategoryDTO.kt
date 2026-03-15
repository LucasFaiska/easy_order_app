package com.sraccelerator.easyorder.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("displayOrder") val displayOrder: Int?,
    @SerializedName("status") val status: String?
)