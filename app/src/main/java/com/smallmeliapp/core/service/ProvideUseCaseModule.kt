package com.smallmeliapp.core.service

import com.smallmeliapp.home.repository.HomeRepository
import com.smallmeliapp.home.repository.HomeSharedPreferencesRepository
import com.smallmeliapp.home.usecase.HomeSearchUseCase
import com.smallmeliapp.home.usecase.HomeSiteUseCase
import com.smallmeliapp.home.usecase.HomeUseCase
import com.smallmeliapp.product.repository.ProductRepository
import com.smallmeliapp.product.usecase.ProductDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideUseCaseModule {

    @Singleton
    @Provides
    fun provideHomeUseCase(homeRepository: HomeRepository) = HomeUseCase(homeRepository)

    @Singleton
    @Provides
    fun provideHomeSiteUseCase(homeSharedPreferencesRepository: HomeSharedPreferencesRepository) =
        HomeSiteUseCase(homeSharedPreferencesRepository)

    @Singleton
    @Provides
    fun provideHomeSearchUseCase(
        homeRepository: HomeRepository,
        homeSharedPreferencesRepository: HomeSharedPreferencesRepository
    ) =
        HomeSearchUseCase(homeRepository, homeSharedPreferencesRepository)

    @Singleton
    @Provides
    fun provideProductDetailUseCase(
        productRepository: ProductRepository
    ) = ProductDetailUseCase(productRepository)
}