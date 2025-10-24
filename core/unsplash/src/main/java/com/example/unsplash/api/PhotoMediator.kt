package com.example.unsplash.api

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.unsplash.local.PhotoEntity
import com.example.unsplash.local.RemoteKey
import com.example.unsplash.local.UnsplashDataBase
import com.example.unsplash.util.toPhotoEntity
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class PhotoMediator(
    private val unsplashDataBase: UnsplashDataBase,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : RemoteMediator<Int, PhotoEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {
        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    unsplashDataBase.remoteKeyDao().remoteKeyByQuery(lastItem.id)?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }
            val response =
                photoRemoteDataSource.getPhotos(photoRemoteDataSource.getQueryByPage(loadKey))
            val endOfPagination = response.result.isEmpty()
            val nextKey = if (endOfPagination) null else loadKey + 1
            unsplashDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    unsplashDataBase.photoDao().deleteAll()
                    unsplashDataBase.remoteKeyDao().deleteRemoteKey()
                }
                response.result.forEach {
                    unsplashDataBase.remoteKeyDao().insertOrReplace(RemoteKey(it.id, nextKey))
                }
                unsplashDataBase.photoDao().insertAll(
                    response.result.map { it.toPhotoEntity() }
                )
            }

            return MediatorResult.Success(endOfPaginationReached = nextKey == null)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }

    }


}