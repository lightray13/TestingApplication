package com.testing.application.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre_list")
    suspend fun genreList(): List<Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(list: List<Genre>)
}