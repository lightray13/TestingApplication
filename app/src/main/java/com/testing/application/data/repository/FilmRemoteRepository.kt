package com.testing.application.data.repository

import com.testing.application.data.network.model.FilmList
import com.testing.application.data.network.service.ApiInterface
import com.testing.application.data.network.BaseRemoteDataSource
import com.testing.application.data.network.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmRemoteRepository @Inject constructor(private val service: ApiInterface): BaseRemoteDataSource() {
    suspend fun getFilms(): Result<FilmList> =
        getResult {
            service.getFilms()
        }
}