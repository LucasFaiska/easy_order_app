package com.sraccelerator.easyorder.hamburgueria.di

import com.sraccelerator.easyorder.core.config.AppConfig
import com.sraccelerator.easyorder.data.local.LocalDataSource
import com.sraccelerator.easyorder.data.local.RoomCategoryLocalDataSource
import com.sraccelerator.easyorder.data.local.RoomProductLocalDataSource
import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.model.Product
import com.sraccelerator.easyorder.hamburgueria.core.HamburgueriaConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HamburgueriaModule {

    @Binds
    @Singleton
    abstract fun bindAppConfig(hamburgueriaConfig: HamburgueriaConfig): AppConfig

    @Binds
    @Singleton
    abstract fun bindCategoryLocalDataSource(
        roomCategoryLocalDataSource: RoomCategoryLocalDataSource
    ): LocalDataSource<Category>

    @Binds
    @Singleton
    abstract fun bindProductLocalDataSource(
        roomProductLocalDataSource: RoomProductLocalDataSource
    ): LocalDataSource<Product>
}
