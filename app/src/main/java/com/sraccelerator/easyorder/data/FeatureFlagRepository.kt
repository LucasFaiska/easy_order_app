package com.sraccelerator.easyorder.data

import com.sraccelerator.easyorder.data.remote.RemoteDataSource
import com.sraccelerator.easyorder.data.remote.dto.response.FeatureConfigDTO
import com.sraccelerator.easyorder.data.remote.dto.response.FeatureFlagsResponse
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiResponse
import javax.inject.Inject
import javax.inject.Singleton

interface FeatureFlagRepository {
    suspend fun fetchFeatureFlags(): Map<String, FeatureConfigDTO>
    fun getCachedFlags(): Map<String, FeatureConfigDTO>
}

@Singleton
internal class FeatureFlagRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : FeatureFlagRepository {

    private var cachedFlags: Map<String, FeatureConfigDTO> = emptyMap()

    override suspend fun fetchFeatureFlags(): Map<String, FeatureConfigDTO> {
        return when (val response = remoteDataSource.getFeatureFlags()) {
            is EasyOrderApiResponse.Success<FeatureFlagsResponse> -> {
                cachedFlags = response.body.features
                cachedFlags
            }
            else -> cachedFlags
        }
    }

    override fun getCachedFlags(): Map<String, FeatureConfigDTO> = cachedFlags
}
