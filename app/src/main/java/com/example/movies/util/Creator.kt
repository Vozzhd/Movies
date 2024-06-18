package com.example.movies.util

import android.content.Context
import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.network.RetrofitNetworkClient
import com.example.movies.data.storage.LocalStorage
import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.api.MoviesRepository
import com.example.movies.domain.impl.MoviesInteractorImpl
import com.example.movies.presentation.poster.PosterPresenter
import com.example.movies.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(
            RetrofitNetworkClient(context),
            LocalStorage(context.getSharedPreferences("local_storage", Context.MODE_PRIVATE))
        )
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }


    fun providePosterPresenter(posterView: PosterView, imageUrl: String): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }


}