package com.testing.application.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment
import com.testing.application.R
import com.testing.application.data.database.FilmEntity
import com.testing.application.ui.base.BaseFragment
import com.testing.application.util.*
import javax.inject.Inject

class InfoFragment : BaseFragment<InfoContract.Presenter>(), InfoContract.View {

    private var filmId: Int? = null

    @Inject
    lateinit var infoPresenter: InfoContract.Presenter

    private lateinit var infoLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    NavHostFragment.findNavController(this@InfoFragment).navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = activityComponent
        component.inject(this)
        infoPresenter.onAttach(this)

        filmId = arguments?.getInt(Constants.EXTRAS_FILM_ID)
        filmId?.let {
            infoPresenter.getDetails(it)
        }

        initViews()
    }

    override fun showDetails(film: FilmEntity) {
        val imageViewFilm = view?.findViewById<ImageView>(R.id.imageViewFilm)
        imageViewFilm?.let {
            ImageLoader.loadImage(imageViewFilm, film.imageUrl.emptyIfNull())
        }
        view?.findViewById<TextView>(R.id.textViewFilmLocalizedName)?.text = film.localizedName.emptyIfNull()
        view?.findViewById<TextView>(R.id.textViewFilmName)?.text = film.name.emptyIfNull()
        view?.findViewById<TextView>(R.id.textViewReleaseYear)?.text = film.year.yearString()
        view?.findViewById<TextView>(R.id.textViewRating)?.text = film.rating.ratingString()
        view?.findViewById<TextView>(R.id.textViewFilmDescription)?.text = film.description.emptyIfNull()
    }

    override fun setPresenter(presenter: InfoContract.Presenter) {
        this.infoPresenter = presenter
    }

    override fun showProgressDialog() {
        infoLoading.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        infoLoading.visibility = View.GONE
    }

    override fun initViews() {
        infoLoading = view?.findViewById(R.id.infoLoading)!!
    }

    override fun onDestroy() {
        super.onDestroy()
        infoPresenter.onDetach()
    }
}