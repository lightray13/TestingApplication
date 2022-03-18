package com.testing.application.ui.base

import com.testing.application.data.repository.FilmDataRepository

abstract class BasePresenter<V : BaseContract.View<*>>(
    protected val dataRepository: FilmDataRepository
): BaseContract.Presenter<V> {

    protected var view: V? = null

    protected val isViewAttached: Boolean
        get() = view != null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

}