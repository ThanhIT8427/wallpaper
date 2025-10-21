package com.example.unsplash.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PhotoApi {

    @GET("photos")
    suspend fun getPhotos(
        @QueryMap query: Map<String, Int>
    ): Response<List<PhotoResponse>>

    @GET("photos")
    suspend fun getPhotoById(@Query("id") id: String)
}