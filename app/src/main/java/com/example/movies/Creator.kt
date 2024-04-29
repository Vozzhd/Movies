package com.example.movies

import android.app.Activity
import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.network.RetrofitNetworkClient
import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.api.MoviesRepository
import com.example.movies.domain.impl.MoviesInteractorImpl
import com.example.movies.presentation.MoviesSearchController
import com.example.movies.presentation.PosterController
import com.example.movies.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }

    fun provideMoviesSearchController(
        activity: Activity,
        adapter: MoviesAdapter
    ): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController (activity: Activity) : PosterController {
        return PosterController(activity)
    }
}