package com.testing.application.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.testing.application.data.repository.FilmDataRepository
import com.testing.application.di.ActivityContext
import com.testing.application.di.PerActivity
import com.testing.application.ui.info.InfoContract
import com.testing.application.ui.info.InfoPresenter
import com.testing.application.ui.list.FilmListContract
import com.testing.application.ui.list.FilmListPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }

    @PerActivity
    @Provides
    fun provideFilmListPresenter(repository: FilmDataRepository): FilmListContract.Presenter {
        return FilmListPresenter(repository)
    }

    @PerActivity
    @Provides
    fun provideInfoPresenter(repository: FilmDataRepository): InfoContract.Presenter {
        return InfoPresenter(repository)
    }
}