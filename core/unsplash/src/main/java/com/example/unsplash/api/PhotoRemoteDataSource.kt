package com.example.unsplash.api

import dagger.Lazy
import javax.inject.Inject

interface PhotoRemoteService {
    suspend fun getPhotos(query: Map<String, Int>): BaseResponse<List<PhotoResponse>>
    fun getQueryByPage(page: Int): Map<String, Int> = mapOf(
        "page" to page,
        "per_page" to 20
    )
}


class PhotoRemoteDataSource @Inject constructor(
    private val photoApi: Lazy<PhotoApi>
): PhotoRemoteService {
    override suspend fun getPhotos(query: Map<String, Int>): BaseResponse<List<PhotoResponse>> {
        val data = photoApi.get().getPhotos(query)
        val totalPage: Int = data.headers()["x-total"]?.toIntOrNull() ?: 1
        return BaseResponse<List<PhotoResponse>>(data.body().orEmpty(), totalPage)
    }
}