package com.example.movies.util

import android.app.Application
import com.example.movies.di.dataModule
import com.example.movies.di.interactorModule
import com.example.movies.di.repositoryModule
import com.example.movies.di.viewModelModule
import com.example.movies.searchMovies.ui.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication : Application() {
    var moviesViewModel : MoviesViewModel? = null
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(dataModule, interactorModule, repositoryModule , viewModelModule)
        }
    }
}