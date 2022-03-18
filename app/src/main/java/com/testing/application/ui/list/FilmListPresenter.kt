package com.testing.application.ui.list

import android.util.Log
import com.testing.application.adapter.FilmModel
import com.testing.application.adapter.Header
import com.testing.application.data.network.Result
import com.testing.application.data.network.successed
import com.testing.application.data.repository.FilmDataRepository
import com.testing.application.ui.base.BasePresenter
import com.testing.application.util.Constants
import kotlinx.coroutines.*
import javax.inject.Inject

class FilmListPresenter @Inject constructor(repository: FilmDataRepository):
    BasePresenter<FilmListContract.View>(repository), FilmListContract.Presenter{

    override fun loadFilms() {
        val job: Job = GlobalScope.launch(Dispatchers.IO) {
            view?.showProgressDialog()

            when(val result = dataRepository.filmListRemote()) {
                is Result.Success -> {
                    if (result.successed) {
                        showFilms()
                    }
                }
                is Result.Error -> {
                    if (dataRepository.filmListLocal().isNotEmpty()) {
                        showFilms()
                    } else {
                        withContext(Dispatchers.Main) {
                            view?.showEmptyList()
                        }
                    }
                }
            }
            view?.hideProgressDialog()
        }
        job.start()
    }

    suspend fun showFilms() {
            val filmList = dataRepository.filmListLocal()
            val genreList = dataRepository.genreListLocal()
            val showList = mutableListOf<FilmModel>()
            showList.add(FilmModel.HeaderModel(Header(Constants.HEADER_GENRES)))
            genreList.forEach{ genre ->
                showList.add(FilmModel.GenreModel(genre))
            }
            showList.add(FilmModel.HeaderModel(Header(Constants.HEADER_FILMS)))
            filmList.forEach { filmEntity ->
                showList.add(FilmModel.FilmEntityModel(filmEntity))
            }
            if(filmList.isNotEmpty() && genreList.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    view?.refreshFilmList(showList)
                }
            } else {
                withContext(Dispatchers.Main) {
                    view?.showEmptyList()
                }
            }
        }

    override fun showFilmsByGenre(genre: String) {
        val job: Job = GlobalScope.launch(Dispatchers.IO) {
            view?.showProgressDialog()
            val filmList = dataRepository.filmListByGenreLocal(genre)
            Log.d("myLogs", filmList.size.toString())
            val genreList = dataRepository.genreListLocal()
            val showList = mutableListOf<FilmModel>()
            showList.add(FilmModel.HeaderModel(Header(Constants.HEADER_GENRES)))
            view?.hideProgressDialog()
            genreList.forEach{ genre ->
                showList.add(FilmModel.GenreModel(genre))
            }
            showList.add(FilmModel.HeaderModel(Header(Constants.HEADER_FILMS)))
            filmList.forEach { filmEntity ->
                showList.add(FilmModel.FilmEntityModel(filmEntity))
            }

            if(filmList.isNotEmpty() && genreList.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    view?.refreshFilmList(showList)
                }
            } else {
                withContext(Dispatchers.Main) {
                    view?.showEmptyList()
                }
            }
        }
        job.start()
    }
}