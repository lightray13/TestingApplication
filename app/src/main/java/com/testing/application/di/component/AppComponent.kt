package com.testing.application.di.component

import android.app.Application
import android.content.Context
import com.testing.application.MyApp
import com.testing.application.data.repository.FilmDataRepository
import com.testing.application.di.ApplicationContext
import com.testing.application.di.module.AppModule
import com.testing.application.di.module.DataModule
import com.testing.application.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class])
interface AppComponent {

    fun getDataRepository(): FilmDataRepository

    fun inject(app: MyApp)

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}