package com.testing.application.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_list")
data class FilmEntity (
    @PrimaryKey val id: Int,
    val localizedName: String?,
    val name: String?,
    val year: Int?,
    val rating: Double?,
    val imageUrl: String?,
    val description: String?,
    val genres: List<String>?
)