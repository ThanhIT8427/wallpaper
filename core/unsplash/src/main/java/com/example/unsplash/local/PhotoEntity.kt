package com.example.unsplash.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tb_photo")
data class PhotoEntity(@PrimaryKey val id: String, val blurhash: String, val source: String)