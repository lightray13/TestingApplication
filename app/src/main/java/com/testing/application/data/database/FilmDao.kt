package com.testing.application.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {

    @Query("SELECT * FROM film_list ORDER BY LOWER(localizedName) ASC")
    suspend fun filmList(): List<FilmEntity>

    @Query("SELECT * FROM film_list WHERE genres LIKE '%' || :genres || '%'")
    suspend fun getFilmListByGenre(genres: String): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<FilmEntity>)

    @Query("SELECT * FROM film_list WHERE id=:id")
    fun filmById(id: Int): FilmEntity?
}