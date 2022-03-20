package com.testing.application.ui.info

import com.testing.application.data.repository.FilmDataRepository
import com.testing.application.ui.base.BasePresenter
import kotlinx.coroutines.*
import javax.inject.Inject

class InfoPresenter @Inject constructor(repository: FilmDataRepository):
    BasePresenter<InfoContract.View>(repository),InfoContract.Presenter {

    override fun getDetails(id: Int) {
        val job: Job = GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                view?.showProgressDialog()
            }
            val result = dataRepository.filmById(id)
            result?.let {
                withContext(Dispatchers.Main) {
                    view?.showDetails(result)
                }
            }
            withContext(Dispatchers.Main) {
                view?.hideProgressDialog()
            }
        }
        job.start()
    }
}