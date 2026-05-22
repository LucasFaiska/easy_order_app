package com.sraccelerator.easyorder.core.featureflag

import com.sraccelerator.easyorder.core.config.AppConfig
import com.sraccelerator.easyorder.data.FeatureFlagRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureFlagManager @Inject constructor(
    private val repository: FeatureFlagRepository,
    private val appConfig: AppConfig
) : FeatureFlagProvider {

    override suspend fun setup() {
        repository.fetchFeatureFlags(appConfig.restaurantId)
    }

    override fun isEnabled(key: FeatureKey): Boolean {
        return repository.getCachedFlags()[key.key]?.enabled ?: false
    }

    override fun getStringValue(key: FeatureKey): String? {
        val config = repository.getCachedFlags()[key.key]
        return if (config?.enabled == true) config.value else null
    }
}
