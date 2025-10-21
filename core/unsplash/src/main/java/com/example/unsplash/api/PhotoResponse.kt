package com.example.unsplash.api


data class BaseResponse<T>(
    val result: T,
    val totalPage: Int
)
data class PhotoResponse(
    val alt_description: String,
    val blur_hash: String,
    val id: String,
    val slug: String,
    val urls: Urls,
    val user: User,
    val totalPage: Int = 0
)

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class User(
    val id: String,
    val name: String,
    val profile_image: ProfileImage,
    val username: String
)

data class ProfileImage(
    val large: String,
    val medium: String,
    val small: String
)