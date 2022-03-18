package com.testing.application.data.repository

import com.testing.application.data.database.FilmEntity
import com.testing.application.data.database.Genre
import javax.inject.Inject
import com.testing.application.data.network.Result
import com.testing.application.data.network.successed
import com.testing.application.util.Constants
import javax.inject.Singleton

@Singleton
class FilmDataRepository @Inject constructor(
    private val filmLocalDataSource: FilmLocalRepository,
    private val filmRemoteDataSource: FilmRemoteRepository
) {
    suspend fun filmListLocal(): List<FilmEntity> {
        return filmLocalDataSource.getFilms()
    }

    suspend fun genreListLocal(): List<Genre> {
        return filmLocalDataSource.getGenres()
    }

    suspend fun filmListByGenreLocal(genre: String): List<FilmEntity> {
        return filmLocalDataSource.getFilmsByGenre(genre)
    }

    suspend fun filmById(id: Int) = filmLocalDataSource.filmById(id)

    suspend fun filmListRemote(): Result<Boolean> {
        val result = filmRemoteDataSource.getFilms()
        if (result is Result.Success) {
            if (result.successed) {
                val resultFilmList = result.data.films.map { film ->
                    FilmEntity(
                        film.id,
                        film.localizedName,
                        film.name,
                        film.year,
                        film.rating,
                        film.imageUrl,
                        film.description,
                        film.genres
                    )
                }
                val resultGenresList = mutableListOf<Genre>()
                for (element in resultFilmList) {
                    element.genres?.let { resultGenresList.addAll(it.map { name ->
                        Genre(
                            name
                        )
                    }) }
                }
                val genreList = resultGenresList.toSet().toList()
                filmLocalDataSource.insertFilmsIntoDatabase(resultFilmList)
                filmLocalDataSource.insertGenresIntoDatabase(genreList)
                return Result.Success(true)
            } else {
                return Result.Error(Constants.GENERIC_ERROR)
            }
        } else {
            return result as Result.Error
        }
    }
}