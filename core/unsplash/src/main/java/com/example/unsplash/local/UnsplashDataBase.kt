package com.example.unsplash.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [PhotoEntity::class, RemoteKey::class], version = 1)
abstract class UnsplashDataBase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}