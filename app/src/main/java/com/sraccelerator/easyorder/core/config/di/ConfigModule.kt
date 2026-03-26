package com.sraccelerator.easyorder.core.config.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {
    // Este módulo está vazio para evitar DuplicateBindings.
    // Cada módulo de aplicativo (ex: :pizzaria) deve fornecer sua própria implementação de AppConfig.
}
