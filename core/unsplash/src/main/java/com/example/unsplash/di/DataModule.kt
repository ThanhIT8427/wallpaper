package com.example.unsplash.di

import com.example.unsplash.api.PhotoApi
import com.example.unsplash.api.PhotoRemoteDataSource
import com.example.unsplash.api.PhotoRemoteService
import com.example.unsplash.api.RetrofitConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun providerRetrofitConfig(): RetrofitConfig = RetrofitConfig()

    @Singleton
    @Provides
    fun providerRetrofit(retrofitConfig: RetrofitConfig): Retrofit =
        Retrofit.Builder().client(retrofitConfig.client).baseUrl(retrofitConfig.baseUrl)
            .addConverterFactory(retrofitConfig.converter).build()

    @Singleton
    @Provides
    fun providerPhotoApi(retrofit: Retrofit): PhotoApi = retrofit.create(PhotoApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Singleton
    @Binds
    fun bindPhotoRemoteDataSource(photoRemoteDataSource: PhotoRemoteDataSource): PhotoRemoteService
}