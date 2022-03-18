package com.testing.application.di.component

import com.testing.application.di.PerActivity
import com.testing.application.di.module.ActivityModule
import com.testing.application.ui.info.InfoFragment
import com.testing.application.ui.list.FilmListFragment
import com.testing.application.ui.main.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: InfoFragment)
    fun inject(fragment: FilmListFragment)
}