package com.testing.application.data.network.service

import com.testing.application.data.network.model.FilmList
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/sequeniatesttask/films.json")
    suspend fun getFilms(): Response<FilmList>
}