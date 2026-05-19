package com.sraccelerator.easyorder.core.featureflag

import com.sraccelerator.easyorder.data.FeatureFlagRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureFlagManager @Inject constructor(
    private val repository: FeatureFlagRepository
) : FeatureFlagProvider {

    override fun isEnabled(key: FeatureKey): Boolean {
        return repository.getCachedFlags()[key.key]?.enabled ?: false
    }

    override fun getStringValue(key: FeatureKey): String? {
        val config = repository.getCachedFlags()[key.key]
        return if (config?.enabled == true) config.value else null
    }
}
