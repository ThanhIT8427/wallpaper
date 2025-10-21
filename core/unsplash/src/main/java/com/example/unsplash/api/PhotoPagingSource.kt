package com.example.unsplash.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class PhotoPagingSource (
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : PagingSource<Int , PhotoResponse>() {

    companion object {
        const val PAGE_DEFAULT = 1
    }

    override fun getRefreshKey(state: PagingState<Int , PhotoResponse>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int , PhotoResponse> {
        val key = params.key ?: PAGE_DEFAULT

        return try {
            val response =
                photoRemoteDataSource.getPhotos(photoRemoteDataSource.getQueryByPage(key))
            val totalPage = response.totalPage
            val datas = response.result
            val prevKey = if (key == PAGE_DEFAULT) null else key - 1
            val nextKey = if (key == totalPage) null else key + 1
            LoadResult.Page(
                datas , prevKey , nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}