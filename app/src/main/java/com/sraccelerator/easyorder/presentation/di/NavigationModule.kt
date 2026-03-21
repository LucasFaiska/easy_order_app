package com.sraccelerator.easyorder.presentation.di

import com.sraccelerator.easyorder.presentation.navigation.Navigator
import com.sraccelerator.easyorder.presentation.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindNavigator(
        impl: NavigatorImpl
    ): Navigator
}