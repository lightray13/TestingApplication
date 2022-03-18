package com.testing.application.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_list")
data class Genre(
    @PrimaryKey val name: String)