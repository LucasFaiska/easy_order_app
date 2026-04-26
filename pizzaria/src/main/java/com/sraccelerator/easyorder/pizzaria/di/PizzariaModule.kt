package com.sraccelerator.easyorder.pizzaria.di

import com.sraccelerator.easyorder.core.config.AppConfig
import com.sraccelerator.easyorder.data.local.CategoryLocalDataSource
import com.sraccelerator.easyorder.data.local.InMemoryCategoryLocalDataSource
import com.sraccelerator.easyorder.data.local.InMemoryProductLocalDataSource
import com.sraccelerator.easyorder.data.local.ProductLocalDataSource
import com.sraccelerator.easyorder.pizzaria.core.PizzariaConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PizzariaModule {

    @Binds
    @Singleton
    abstract fun bindAppConfig(pizzariaConfig: PizzariaConfig): AppConfig

    @Binds
    @Singleton
    abstract fun bindCategoryLocalDataSource(
        inMemoryCategoryLocalDataSource: InMemoryCategoryLocalDataSource
    ): CategoryLocalDataSource

    @Binds
    @Singleton
    abstract fun bindProductLocalDataSource(
        inMemoryProductLocalDataSource: InMemoryProductLocalDataSource
    ): ProductLocalDataSource
}
