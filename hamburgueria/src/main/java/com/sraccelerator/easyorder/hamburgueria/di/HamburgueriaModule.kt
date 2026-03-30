package com.sraccelerator.easyorder.hamburgueria.di

import com.sraccelerator.easyorder.core.config.AppConfig
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
}
