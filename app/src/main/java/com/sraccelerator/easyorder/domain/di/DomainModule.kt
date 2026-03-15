package com.sraccelerator.easyorder.domain.di

import com.sraccelerator.easyorder.domain.usecase.GetCategoriesUseCase
import com.sraccelerator.easyorder.domain.usecase.GetCategoriesUseCaseImpl
import com.sraccelerator.easyorder.domain.usecase.GetProductsByCategoryUseCase
import com.sraccelerator.easyorder.domain.usecase.GetProductsByCategoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindGetCategoriesUseCase(
        impl: GetCategoriesUseCaseImpl
    ): GetCategoriesUseCase

    @Binds
    @Singleton
    abstract fun bindGetProductsByCategoryUseCase(
        impl: GetProductsByCategoryUseCaseImpl
    ): GetProductsByCategoryUseCase
}