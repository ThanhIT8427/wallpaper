package com.example.unsplash.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unsplash.api.User


@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotoEntity>)

    @Query("Select * from tb_photo")
    fun pagingSource(): PagingSource<Int, PhotoEntity>

    @Query("Delete from tb_photo")
    fun deleteAll()
}