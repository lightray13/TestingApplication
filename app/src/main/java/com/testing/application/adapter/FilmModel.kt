package com.testing.application.adapter

import com.testing.application.data.database.FilmEntity
import com.testing.application.data.database.Genre

sealed class FilmModel {

    class FilmEntityModel(val filmEntity: FilmEntity): FilmModel()

    class GenreModel(val genre: Genre): FilmModel()

    class HeaderModel(val header: Header): FilmModel()
}