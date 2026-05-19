package com.sraccelerator.easyorder.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class FeatureFlagsResponse(
    @SerializedName("features")
    val features: Map<String, FeatureConfigDTO>
)

data class FeatureConfigDTO(
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String?
)
