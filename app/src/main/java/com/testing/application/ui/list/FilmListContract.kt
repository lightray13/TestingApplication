package com.testing.application.ui.list

import com.testing.application.adapter.FilmModel
import com.testing.application.ui.base.BaseContract

interface FilmListContract {

    interface View: BaseContract.View<Presenter> {

        fun refreshFilmList(filmList: List<FilmModel>)

        fun showEmptyList()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadFilms()

        fun showFilmsByGenre(genre: String)
    }
}