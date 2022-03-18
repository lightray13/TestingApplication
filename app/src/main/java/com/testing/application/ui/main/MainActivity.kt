package com.testing.application.ui.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.testing.application.R
import com.testing.application.ui.base.*

class MainActivity : BaseActivity(), NavigationHost {

    companion object {
        private val TOOLBAR_DESTINATION = setOf(
            R.id.filmListFragment,
            R.id.infoFragment
        )
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationHostFragment) as? NavHostFragment
            ?: return
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(TOOLBAR_DESTINATION)

    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        navController?.apply {
            toolbar.setupWithNavController(this, appBarConfiguration)
        }

        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.infoFragment -> {
                    toolbar.setNavigationIcon(R.drawable.baseline_arrow_back)
                }
            }
        }
    }
}