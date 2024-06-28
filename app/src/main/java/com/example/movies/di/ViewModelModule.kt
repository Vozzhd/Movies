package com.example.movies.di

import com.example.movies.details.ui.about.AboutViewModel
import com.example.movies.details.ui.poster.PosterViewModel
import com.example.movies.searchMovies.ui.models.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesViewModel(get(), androidApplication())
    }

    viewModel { (posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

    viewModel { (movieId: String) ->
        AboutViewModel(movieId, get())
    }
}