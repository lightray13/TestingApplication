package com.testing.application.di.module

import android.app.Application
import android.content.Context
import com.testing.application.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }
}