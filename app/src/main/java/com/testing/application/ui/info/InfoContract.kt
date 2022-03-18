package com.testing.application.ui.info

import com.testing.application.data.database.FilmEntity
import com.testing.application.ui.base.BaseContract

interface InfoContract {

    interface View: BaseContract.View<Presenter> {

        fun showDetails(film: FilmEntity)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getDetails(id: Int)
    }
}