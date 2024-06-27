package com.example.movies.di

import com.example.movies.presentation.movies.MoviesSearchViewModel
import com.example.movies.presentation.poster.PosterViewModel
import com.example.movies.ui.about.AboutViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(get(), androidApplication())
    }

    viewModel { (posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

    viewModel { (movieId: String) ->
        AboutViewModel(movieId, get())
    }
}