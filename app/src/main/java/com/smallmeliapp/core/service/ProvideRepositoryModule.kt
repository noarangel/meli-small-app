package com.smallmeliapp.core.service

import com.smallmeliapp.home.api.HomeDataSource
import com.smallmeliapp.home.repository.HomeRepository
import com.smallmeliapp.home.repository.HomeSharedPreferencesRepository
import com.smallmeliapp.product.api.ProductDataSource
import com.smallmeliapp.product.repository.ProductRepository
import com.smallmeliapp.utils.SharedPreferencesUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideRepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(homeDataSource: HomeDataSource) = HomeRepository(homeDataSource)

    @Singleton
    @Provides
    fun provideHomeSharedPreferencesRepository(sharedPreferencesUtil: SharedPreferencesUtil) =
        HomeSharedPreferencesRepository(sharedPreferencesUtil)

    @Singleton
    @Provides
    fun provideProductRepository(productDataSource: ProductDataSource) =
        ProductRepository(productDataSource)
}