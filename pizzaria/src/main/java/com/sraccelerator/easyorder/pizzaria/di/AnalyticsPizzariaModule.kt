package com.sraccelerator.easyorder.pizzaria.di

import com.sraccelerator.easyorder.analytics.AnalyticsHandler
import com.sraccelerator.easyorder.analytics.loghandler.LogAnalyticsHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsPizzariaModule {

    @Binds
    @Singleton
    abstract fun provideAnalyticsHandler(logAnalyticsHandler: LogAnalyticsHandler): AnalyticsHandler
}