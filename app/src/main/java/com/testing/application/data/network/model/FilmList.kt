package com.testing.application.data.network.model

import com.google.gson.annotations.SerializedName

data class FilmList(
    @SerializedName("films")
    val films: List<Film>
)