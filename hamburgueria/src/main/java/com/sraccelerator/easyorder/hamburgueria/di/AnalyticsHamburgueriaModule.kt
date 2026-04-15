package com.sraccelerator.easyorder.hamburgueria.di

import com.sraccelerator.easyorder.analytics.AnalyticsHandler
import com.sraccelerator.easyorder.analytics.loghandler.FirebaseHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsHamburgueriaModule {

    @Binds
    @Singleton
    abstract fun provideAnalyticsHandler(firebaseHandler: FirebaseHandler): AnalyticsHandler
}