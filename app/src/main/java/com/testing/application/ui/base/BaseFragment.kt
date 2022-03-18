package com.testing.application.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.testing.application.R
import com.testing.application.di.component.ActivityComponent
import com.testing.application.util.Constants

interface NavigationHost {
    fun registerToolbarWithNavigation(toolbar: Toolbar)
}

abstract class BaseFragment<T>: Fragment(), BaseContract.View<T>  {

    private var navigationHost: NavigationHost? = null

    private var itemView: View? = null

    private var progressDialog: Dialog? = null

    protected lateinit var activityComponent: ActivityComponent

    protected lateinit var basePresenter: BaseContract.Presenter<T>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is NavigationHost) {
            navigationHost = context
        }
        if(context is BaseActivity) {
            this.activityComponent = context.activityComponent
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemView = view
    }

    override fun onResume() {
        super.onResume()
        val host = navigationHost ?: return
        val mainToolbar: Toolbar = itemView?.findViewById(R.id.toolbar) ?: return

        host.registerToolbarWithNavigation(mainToolbar)
    }

    override fun onDetach() {
        super.onDetach()
        navigationHost = null
        itemView = null
    }

    override fun navigate(resId: Int, id: Int) {
        val bundle = bundleOf(Constants.EXTRAS_FILM_ID to id)
        findNavController().navigate(resId, bundle)
    }

    override fun showError(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showError(stringResId: Int) {
        Toast.makeText(context, getString(stringResId), Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(srtResId: Int) {
        Toast.makeText(context, getString(srtResId, Toast.LENGTH_SHORT), Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
    }

    override fun setPresenter(presenter: T) {
        basePresenter = presenter as BaseContract.Presenter<T>
    }
}