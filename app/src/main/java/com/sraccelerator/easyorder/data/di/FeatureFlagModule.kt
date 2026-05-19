package com.sraccelerator.easyorder.data.di

import com.sraccelerator.easyorder.core.featureflag.FeatureFlagManager
import com.sraccelerator.easyorder.core.featureflag.FeatureFlagProvider
import com.sraccelerator.easyorder.data.FeatureFlagRepository
import com.sraccelerator.easyorder.data.FeatureFlagRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FeatureFlagModule {

    @Binds
    @Singleton
    internal abstract fun bindFeatureFlagRepository(
        impl: FeatureFlagRepositoryImpl
    ): FeatureFlagRepository

    @Binds
    @Singleton
    internal abstract fun bindFeatureFlagProvider(
        impl: FeatureFlagManager
    ): FeatureFlagProvider
}
