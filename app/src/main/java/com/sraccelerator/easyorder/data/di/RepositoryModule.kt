package com.sraccelerator.easyorder.data.di

import com.sraccelerator.easyorder.data.CartRepository
import com.sraccelerator.easyorder.data.CartRepositoryImpl
import com.sraccelerator.easyorder.data.EasyOrderRepository
import com.sraccelerator.easyorder.data.EasyOrderRepositoryImpl
import com.sraccelerator.easyorder.data.remote.RemoteDataSource
import com.sraccelerator.easyorder.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: EasyOrderRepositoryImpl
    ): EasyOrderRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository

}