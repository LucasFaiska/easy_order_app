package com.sraccelerator.easyorder.domain.di

import com.sraccelerator.easyorder.domain.usecase.AddProductToCartUseCase
import com.sraccelerator.easyorder.domain.usecase.AddProductToCartUseCaseImpl
import com.sraccelerator.easyorder.domain.usecase.GetCartItemsCountUseCase
import com.sraccelerator.easyorder.domain.usecase.GetCartItemsCountUseCaseImpl
import com.sraccelerator.easyorder.domain.usecase.GetCartUseCase
import com.sraccelerator.easyorder.domain.usecase.GetCartUseCaseImpl
import com.sraccelerator.easyorder.domain.usecase.GetCategoriesUseCase
import com.sraccelerator.easyorder.domain.usecase.GetCategoriesUseCaseImpl
import com.sraccelerator.easyorder.domain.usecase.GetProductsByCategoryUseCase
import com.sraccelerator.easyorder.domain.usecase.GetProductsByCategoryUseCaseImpl
import com.sraccelerator.easyorder.domain.usecase.RemoveProductFromCartUseCase
import com.sraccelerator.easyorder.domain.usecase.RemoveProductFromCartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
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

    @Binds
    internal abstract fun bindGetCartItemsCountUseCase(
        impl: GetCartItemsCountUseCaseImpl
    ): GetCartItemsCountUseCase

    @Binds
    internal abstract fun bindAddProductToCartUseCase(
        impl: AddProductToCartUseCaseImpl
    ): AddProductToCartUseCase

    @Binds
    internal abstract fun bindGetCartUseCase(
        impl: GetCartUseCaseImpl
    ): GetCartUseCase

    @Binds
    internal abstract fun bindRemoveProductFromCartUseCase(
        impl: RemoveProductFromCartUseCaseImpl
    ): RemoveProductFromCartUseCase
}