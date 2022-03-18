package com.testing.application.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testing.application.R
import com.testing.application.adapter.FilmListAdapter
import com.testing.application.adapter.FilmModel
import com.testing.application.adapter.OnItemClickCallback
import com.testing.application.ui.base.BaseFragment
import javax.inject.Inject

class FilmListFragment : BaseFragment<FilmListContract.Presenter>(), FilmListContract.View,
    OnItemClickCallback {

    @Inject
    lateinit var filmListPresenter: FilmListContract.Presenter

    private var filmListAdapter = FilmListAdapter(this)

    private lateinit var recyclerView: RecyclerView
    private lateinit var textError: TextView
    private lateinit var filmListLoading: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_film_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = activityComponent
        component.inject(this)
        filmListPresenter.onAttach(this)
        filmListPresenter.loadFilms()

        initViews()
    }

    override fun refreshFilmList(filmList: List<FilmModel>) {
        recyclerView.visibility = View.VISIBLE
        textError.visibility = View.GONE
        filmListAdapter.updateList(filmList)
    }

    override fun showEmptyList() {
        recyclerView.visibility = View.GONE
        textError.visibility = View.VISIBLE
    }

    override fun setPresenter(presenter: FilmListContract.Presenter) {
        this.filmListPresenter = presenter
    }

    override fun initViews() {
        filmListLoading = view?.findViewById(R.id.filmListLoading)!!
        textError = view?.findViewById<TextView>(R.id.textError)!!
        textError.setOnClickListener(clickListener)
        recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)!!
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) =  if (position < 15) 2 else 1
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = filmListAdapter
    }

    override fun onDestroy() {
        filmListPresenter.onDetach()
        super.onDestroy()
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.textError -> filmListPresenter.loadFilms()
        }
    }

    override fun onGenreClick(genre: String) {
        filmListPresenter.showFilmsByGenre(genre)
    }

    override fun onItemClick(id: Int) {
            navigate(R.id.action_filmListFragment_to_infoFragment, id)
    }

    override fun showProgressDialog() {
        filmListLoading.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        filmListLoading.visibility = View.GONE
    }
}