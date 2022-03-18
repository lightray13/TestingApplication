package com.testing.application

import android.app.Application
import com.testing.application.data.repository.FilmDataRepository
import com.testing.application.di.component.AppComponent
import com.testing.application.di.component.DaggerAppComponent
//import com.testing.application.di.component.DaggerAppComponent
import com.testing.application.di.module.AppModule
import com.testing.application.di.module.DataModule
import com.testing.application.di.module.NetworkModule
import javax.inject.Inject

class MyApp: Application() {

    @Inject
    lateinit var repository: FilmDataRepository

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule())
            .networkModule(NetworkModule())
            .build()

        component.inject(this)

        instance = this
    }

    companion object {
        lateinit var instance: Application
    }
}