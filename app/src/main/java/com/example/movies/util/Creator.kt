package com.example.movies.util

import android.app.Activity
import android.content.Context
import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.network.RetrofitNetworkClient
import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.api.MoviesRepository
import com.example.movies.domain.impl.MoviesInteractorImpl
import com.example.movies.presentation.MoviesSearchController
import com.example.movies.presentation.PosterController
import com.example.movies.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(context:Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchController(
        activity: Activity,
        adapter: MoviesAdapter
    ): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}