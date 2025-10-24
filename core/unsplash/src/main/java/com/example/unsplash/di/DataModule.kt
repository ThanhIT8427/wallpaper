package com.example.unsplash.di

import android.content.Context
import androidx.room.Room
import com.example.unsplash.api.PhotoApi
import com.example.unsplash.api.PhotoRemoteDataSource
import com.example.unsplash.api.PhotoRemoteService
import com.example.unsplash.api.RetrofitConfig
import com.example.unsplash.local.UnsplashDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun providerDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = UnsplashDataBase::class.java, name = "unsplash_database"
    ).build()

    @Singleton
    @Provides
    fun providerPhotoDao(unsplashDataBase: UnsplashDataBase) = unsplashDataBase.photoDao()
}

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Singleton
    @Binds
    fun bindPhotoRemoteDataSource(photoRemoteDataSource: PhotoRemoteDataSource): PhotoRemoteService
}