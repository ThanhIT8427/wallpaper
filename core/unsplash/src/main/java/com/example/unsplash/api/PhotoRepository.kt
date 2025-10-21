package com.example.unsplash.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoRemoteDataSource: PhotoRemoteDataSource
) {
    val pager = Pager(
        config = PagingConfig(20) ,
        pagingSourceFactory = { PhotoPagingSource(photoRemoteDataSource) }
    ).flow
}