package com.testing.application.di.module

import android.content.Context
import com.testing.application.data.database.FilmDatabase
import com.testing.application.data.network.service.ApiInterface
import com.testing.application.data.repository.FilmDataRepository
import com.testing.application.data.repository.FilmLocalRepository
import com.testing.application.data.repository.FilmRemoteRepository
import com.testing.application.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FilmDatabase = FilmDatabase.buildDatabase(context)

    @Provides
    @Singleton
    fun provideFilmLocalRepository(database: FilmDatabase): FilmLocalRepository = FilmLocalRepository(database)

    @Provides
    @Singleton
    fun provideFilmRemoteRepository(apiInterface: ApiInterface): FilmRemoteRepository = FilmRemoteRepository(apiInterface)

    @Provides
    @Singleton
    fun provideFilmDataRepository(filmLocalRepository: FilmLocalRepository, filmRemoteRepository: FilmRemoteRepository): FilmDataRepository = FilmDataRepository(filmLocalRepository, filmRemoteRepository)
}