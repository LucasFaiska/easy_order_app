package com.sraccelerator.easyorder.core.featureflag

interface FeatureFlagProvider {
    fun isEnabled(key: FeatureKey): Boolean
    fun getStringValue(key: FeatureKey): String?
}
