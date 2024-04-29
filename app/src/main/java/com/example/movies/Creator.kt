package com.example.movies

import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.network.RetrofitNetworkClient
import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.api.MoviesRepository
import com.example.movies.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }
}