package com.example.movies.di

import com.example.movies.details.ui.about.AboutViewModel
import com.example.movies.details.ui.cast.MovieCastViewModel
import com.example.movies.details.ui.poster.PosterViewModel
import com.example.movies.history_ui.HistoryViewModel
import com.example.movies.movie_search.ui.models.MoviesViewModel
import com.example.movies.person_search.ui.model.NamesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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

    viewModel { (movieId: String) ->
        MovieCastViewModel(movieId, get())
    }

    viewModel {
        NamesViewModel(androidContext(), get())
    }

    viewModel {
        HistoryViewModel(androidContext(), get())
    }
}