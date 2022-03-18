package com.testing.application.ui.base

import androidx.annotation.StringRes

interface BaseContract {

    interface View<T> {

        fun navigate(resId: Int, id: Int)

        fun setPresenter(presenter: T)

        fun showProgressDialog()

        fun hideProgressDialog()

        fun showError(error: String?)

        fun showError(@StringRes stringResId: Int)

        fun showMessage(@StringRes srtResId: Int)

        fun showMessage(message: String)

        fun initViews()
    }

    interface Presenter<V> {

        fun onAttach(view: V)

        fun onDetach()

    }
}