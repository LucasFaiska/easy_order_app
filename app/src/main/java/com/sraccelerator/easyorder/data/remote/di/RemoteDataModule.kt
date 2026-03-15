package com.sraccelerator.easyorder.data.remote.di

import com.sraccelerator.easyorder.data.remote.RemoteDataSource
import com.sraccelerator.easyorder.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataModule {

    @Binds
    @Singleton
    fun bindRemoteDataSource(
        dataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource
}