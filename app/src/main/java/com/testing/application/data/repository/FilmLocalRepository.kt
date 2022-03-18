package com.testing.application.data.repository

import com.testing.application.data.database.FilmDatabase
import com.testing.application.data.database.FilmEntity
import com.testing.application.data.database.Genre
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmLocalRepository @Inject constructor(private val database: FilmDatabase) {

    suspend fun getFilms(): List<FilmEntity> {
        return database.filmDao().filmList()
    }

    suspend fun getGenres(): List<Genre> {
        return database.genreDao().genreList()
    }

    suspend fun getFilmsByGenre(genre: String): List<FilmEntity> {
        return database.filmDao().getFilmListByGenre(genre)
    }

    suspend fun filmById(id: Int) = database.filmDao().filmById(id)

    suspend fun insertGenresIntoDatabase(genres: List<Genre>) {
        if (genres.isNotEmpty()) {
            database.genreDao().insertGenres(genres)
        }
    }

    suspend fun insertFilmsIntoDatabase(films: List<FilmEntity>) {
        if(films.isNotEmpty()) {
            database.filmDao().insert(films)
        }
    }
}