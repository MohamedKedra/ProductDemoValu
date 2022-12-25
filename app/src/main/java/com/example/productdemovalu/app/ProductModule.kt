package com.example.productdemovalu.app

import android.content.Context
import com.example.productdemovalu.remote.ProductService
import com.example.productdemovalu.repository.ProductRepository
import com.example.productdemovalu.utils.ConnectionManager
import com.example.productdemovalu.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductModule {
    @Singleton
    @Provides
    fun providesRetrofit() = Retrofit.Builder()
        .baseUrl(Constant.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) = retrofit.create(ProductService::class.java)

    @Singleton
    @Provides
    fun provideRepository(service: ProductService) = ProductRepository(service)

    @Singleton
    @Provides
    fun provideNetwork(@ApplicationContext context: Context) =
        ConnectionManager(context)
}