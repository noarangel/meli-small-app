package com.smallmeliapp.core.service

import com.smallmeliapp.home.api.HomeDataSource
import com.smallmeliapp.product.api.ProductDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideApiModule {

    @Singleton
    @Provides
    fun provideHomeDataSource(retrofit: Retrofit): HomeDataSource =
        retrofit.create(HomeDataSource::class.java)

    @Singleton
    @Provides
    fun provideProductDataSource(retrofit: Retrofit): ProductDataSource =
        retrofit.create(ProductDataSource::class.java)
}