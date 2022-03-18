package com.testing.application.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.testing.application.MyApp
import com.testing.application.di.component.ActivityComponent
import com.testing.application.di.component.DaggerActivityComponent
import com.testing.application.di.module.ActivityModule

abstract class BaseActivity: AppCompatActivity()  {

    val activityComponent: ActivityComponent by lazy { initActivityComponent()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as MyApp).component)
            .build()
    }
}