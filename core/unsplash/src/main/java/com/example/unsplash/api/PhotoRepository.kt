package com.example.unsplash.api

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.unsplash.local.UnsplashDataBase
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val unsplashDataBase: UnsplashDataBase,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) {
    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(20) ,
        remoteMediator = PhotoMediator(unsplashDataBase, photoRemoteDataSource)
    ) {
        unsplashDataBase.photoDao().pagingSource()
    }.flow
}