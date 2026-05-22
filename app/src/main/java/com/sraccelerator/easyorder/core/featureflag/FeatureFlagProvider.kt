package com.sraccelerator.easyorder.core.featureflag

interface FeatureFlagProvider {

    suspend fun setup()
    fun isEnabled(key: FeatureKey): Boolean
    fun getStringValue(key: FeatureKey): String?
}
