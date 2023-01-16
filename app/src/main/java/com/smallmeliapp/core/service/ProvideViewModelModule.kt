package com.smallmeliapp.core.service

import com.smallmeliapp.home.usecase.HomeSearchUseCase
import com.smallmeliapp.home.usecase.HomeSiteUseCase
import com.smallmeliapp.home.usecase.HomeUseCase
import com.smallmeliapp.home.viewmodel.HomeViewModel
import com.smallmeliapp.product.usecase.ProductDetailUseCase
import com.smallmeliapp.product.viewmodel.ProductViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideViewModelModule {

    @Singleton
    @Provides
    fun provideHomeViewModel(
        homeUseCase: HomeUseCase,
        homeSiteUseCase: HomeSiteUseCase,
        homeSearchUseCase: HomeSearchUseCase
    ) =
        HomeViewModel(homeUseCase, homeSiteUseCase, homeSearchUseCase)

    @Singleton
    @Provides
    fun provideProductViewModel(
        productDetailUseCase: ProductDetailUseCase
    ) =
        ProductViewModel(productDetailUseCase)
}