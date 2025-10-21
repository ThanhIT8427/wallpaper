package com.example.unsplash.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitConfig @Inject constructor() {
    val baseUrl = "https://api.unsplash.com/"
    private val accessKey = "2tFi5dPVbHsD-FzW6PrYTfhheay0t577PMkU05W0gfI"
    private val httpLogging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val newRequest = original.newBuilder().apply {
                addHeader("Authorization", "Client-ID $accessKey")
            }.build()
            return chain.proceed(newRequest)
        }

    }

    val client = OkHttpClient.Builder().addInterceptor(httpLogging).addInterceptor(interceptor).build()
    val converter = GsonConverterFactory.create()
}